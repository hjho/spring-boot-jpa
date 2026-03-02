package com.document.study.jpa.business.specific;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class SpecBuilder {
    public static <T> Builder<T> builder(Class<T> type) {
        return new Builder<T>();
    }

    public static class Builder<T> {
        private List<Specification<T>> specs = new ArrayList<>();

        private void addSpec(Specification<T> spec) {
            if (spec != null) {
                specs.add(spec);
            }
        }

        public Builder<T> and(Specification<T> spec) {
            addSpec(spec);
            return this;
        }

        public Builder<T> ifHasText(String str, Function<String, Specification<T>> specSupplier) {
            if (StringUtils.hasText(str)) {
                addSpec(specSupplier.apply(str));
            }
            return this;
        }

        public Builder<T> ifTrue(Boolean cond, Supplier<Specification<T>> specSupplier) {
            if (cond != null && cond.booleanValue()) {
                addSpec(specSupplier.get());
            }
            return this;
        }

        public <V> Builder<T> ifNotNull(V value, Function<V, Specification<T>> specSupplier) {
            if (value != null) {
                addSpec(specSupplier.apply(value));
            }
            return this;
        }

        public Specification<T> build() {
            Specification<T> spec = null; // where 1=1: (root, query, cb) -> cb.conjunction();
            for (Specification<T> s : specs) {
            	spec = (spec == null) ? s : spec.and(s);
            }
            return spec;
        }
    }
}