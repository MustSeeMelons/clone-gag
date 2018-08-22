package com.strautins.CloneGag.model.validators;

import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.utils.ImageUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class PostValidator implements Validator {

    // comma separated words
    private static Pattern tagPattern = Pattern.compile("(\\d+)(,\\s*\\d+)*");

    @Override
    public boolean supports(Class<?> aClass) {
        return Post.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "title.empty");

        Post post = (Post) o;
        if (post.getTags() != null && tagPattern.matcher(post.getTags()).matches()) {
            errors.rejectValue("tags", "tags.invalid");
        }

        if (ImageUtils.getSupportedMime(post.getImage()) == null) {
            errors.rejectValue("image", "image.not");
        }


    }
}
