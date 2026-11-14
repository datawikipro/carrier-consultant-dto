package pro.datawiki.carrier.dto.jobsite;

import lombok.Getter;

@Getter
public abstract class AbstractJobSite implements JobSite {

    @Override
    public boolean supportsResumeBoost() {
        return getResumeBoostSelector() != null && !getResumeBoostSelector().isBlank();
    }

    @Override
    public boolean supportsDirectApply() {
        return true;
    }

    @Override
    public boolean supportsChat() {
        return true;
    }

    @Override
    public String toString() {
        return getCode() + " (" + getDisplayName() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobSite other)) return false;
        return getCode().equalsIgnoreCase(other.getCode());
    }

    @Override
    public int hashCode() {
        return getCode().toUpperCase().hashCode();
    }
}
