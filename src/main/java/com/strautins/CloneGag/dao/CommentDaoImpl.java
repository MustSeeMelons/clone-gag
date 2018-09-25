package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Comment;
import com.strautins.CloneGag.model.hql.CommentData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.NoResultException;

@Repository
public class CommentDaoImpl implements CommentDao {
    
    private static int PAGE_SIZE = 5;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<CommentData> getPostComments(BigInteger postId, BigInteger page) {
        String hql = "select new com.strautins.CloneGag.model.hql.CommentData(c.id, "
                + "c.owner, "
                + "u.username, "
                + "c.points, "
                + "c.createDate, "
                + "c.value,"
                + "(select count(*) from Comment cc where c.id = cc.parentCommentId)) "
                + "from Comment c, CloneGagUser u "
                + "where c.owner = u.id and c.postId = :postId and c.parentCommentId is null "
                + "order by c.points DESC";
        
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("postId", postId)
                .setFirstResult(page.intValue() * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<CommentData> getCommentReplies(BigInteger commentId, BigInteger page) {
        String hql = "select new com.strautins.CloneGag.model.hql.CommentData(c.id, "
                + "c.owner, "
                + "u.username, "
                + "c.points, "
                + "c.createDate, "
                + "c.value) "
                + "from Comment c, CloneGagUser u "
                + "where c.owner = u.id and c.parentCommentId = :parentCommentId "
                + "order by c.points DESC";
        
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("parentCommentId", commentId)
                .setFirstResult(page.intValue() * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .list();
    }
    
    @Override
    public Comment loadComment(BigInteger id) {
        try {
            return sessionFactory.getCurrentSession().get(Comment.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public void updateComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }
}
