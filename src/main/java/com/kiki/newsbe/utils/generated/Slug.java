package com.kiki.newsbe.utils.generated;

public class Slug {
    private final String value;

    public Slug(String name) {
        this.value = generateSlug(name);
    }

    public String getValue() {
        return value;
    }

    public static String of(String name) {
        return new Slug(name).getValue();
    }

    private String generateSlug(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }

        // Convert to lowercase
        String slug = name.toLowerCase();

        // Replace spaces with hyphens
        slug = slug.replaceAll("\\s+", "-");

        // Remove special characters except hyphens
        slug = slug.replaceAll("[^a-z0-9-]", "");

        // Remove multiple consecutive hyphens
        slug = slug.replaceAll("-+", "-");

        // Remove leading and trailing hyphens
        slug = slug.replaceAll("^-+|-+$", "");

        return slug;
    }

    @Override
    public String toString() {
        return value;
    }
}