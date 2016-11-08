package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * <p>
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * <p>
 * Company Tags: Microsoft, Facebook
 * Tags: Stack, String
 */
public class SimplifyPath {

    /**
     * Stack.
     * Split path with slash, there can be 4 situations:
     * 1) A correct name, push into stack.
     * 2) A dot, skip.
     * 3) Double dot, should pop last directory from stack, if not empty.
     * 4) Empty, skip.
     * Finally, go through stack and concatenate words.
     */
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        Deque<String> s = new ArrayDeque<>();
        String[] words = path.split("/");
        for (String str : words) {
            if (str.length() == 0 || str.equals(".")) { // Empty or 1 dot, skip.
                continue;
            }
            if (str.equals("..")) { // Double dots, pop.
                if (!s.isEmpty()) { // Note that we should check whether stack is empty.
                    s.pop();
                }
            } else { // A valid word, push.
                s.push(str);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!s.isEmpty()) {
            res.insert(0, s.pop()).insert(0, "/");
        }
        return res.length() == 0 ? "/" : res.toString();
    }

    @Test
    public void testExamples() {
        Assert.assertEquals("/home", simplifyPath("/home/"));
        Assert.assertEquals("/c", simplifyPath("/a/./b/../../c/"));
        Assert.assertEquals("/", simplifyPath("/../"));
        Assert.assertEquals("/home/foo", simplifyPath("/home//foo/"));
        Assert
            .assertEquals("/e/f/g", simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
    }
}
