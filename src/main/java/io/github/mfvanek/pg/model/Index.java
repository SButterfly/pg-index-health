/*
 * Copyright (c) 2019-2020. Ivan Vakhrushev and others.
 * https://github.com/mfvanek/pg-index-health
 *
 * This file is a part of "pg-index-health" - a Java library for
 * analyzing and maintaining indexes health in PostgreSQL databases.
 *
 * Licensed under the Apache License 2.0
 */

package io.github.mfvanek.pg.model;

import io.github.mfvanek.pg.utils.Validators;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A base representation of database index.
 *
 * @author Ivan Vakhrushev
 * @see TableNameAware
 * @see IndexNameAware
 */
public class Index implements TableNameAware, IndexNameAware, Comparable<Index> {

    private final String tableName;
    private final String indexName;

    @SuppressWarnings("WeakerAccess")
    protected Index(@Nonnull final String tableName, @Nonnull final String indexName) {
        this.tableName = Validators.tableNameNotBlank(tableName);
        this.indexName = Validators.indexNameNotBlank(indexName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public String getTableName() {
        return tableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public String getIndexName() {
        return indexName;
    }

    @Override
    public String toString() {
        return Index.class.getSimpleName() + '{' + innerToString() + '}';
    }

    @SuppressWarnings("WeakerAccess")
    protected String innerToString() {
        return "tableName='" + tableName + '\'' +
                ", indexName='" + indexName + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Index that = (Index) o;
        return tableName.equals(that.tableName) &&
                indexName.equals(that.indexName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, indexName);
    }

    @Override
    public int compareTo(@Nonnull Index other) {
        Objects.requireNonNull(other, "other");
        if (!tableName.equals(other.tableName)) {
            return tableName.compareTo(other.tableName);
        }
        return indexName.compareTo(other.indexName);
    }

    /**
     * Constructs an {@code Index} object.
     *
     * @param tableName table name; should be non blank.
     * @param indexName index name; should be non blank.
     * @return {@code Index}
     */
    public static Index of(@Nonnull final String tableName, @Nonnull final String indexName) {
        return new Index(tableName, indexName);
    }
}
