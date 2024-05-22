package com.mugja.review.domain;

import java.io.Serializable;
import java.util.Objects;

public class RVId implements Serializable {
    private Integer rvId;
    private Integer memId;
    private Integer hostId;

    public RVId() {}

    public RVId(Integer rvId, Integer memId, Integer hostId) {
        this.rvId = rvId;
        this.memId = memId;
        this.hostId = hostId;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RVId rvId = (RVId) o;
        return Objects.equals(rvId, rvId.rvId) &&
                Objects.equals(memId, rvId.memId) &&
                Objects.equals(hostId, rvId.hostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rvId, memId, hostId);
    }
}
