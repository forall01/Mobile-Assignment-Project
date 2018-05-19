package forallstudio.mobilephone.data.source;

import io.realm.Sort;

public enum MobileSortType {

    RATING("rating", Sort.DESCENDING),
    PRICE_LOW_TO_HIGH("price", Sort.ASCENDING),
    PRICE_HIGH_TO_LOW("price", Sort.DESCENDING);

    private String fieldName;
    private Sort sort;

    MobileSortType(String fieldName, Sort sort) {
        this.fieldName = fieldName;
        this.sort = sort;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Sort getSort() {
        return sort;
    }

}