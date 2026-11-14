package pro.datawiki.carrier.dto.track;

import java.util.*;

public final class CareerTracks {

    private static final Map<String, CareerTrack> BY_CODE = new HashMap<>();
    private static final Map<String, CareerTrack> BY_SLUG = new HashMap<>();
    private static final List<CareerTrack> ALL = new ArrayList<>();

    static {
        register(BackendJavaTrack.INSTANCE);
    }

    private CareerTracks() {}

    private static void register(CareerTrack track) {
        BY_CODE.put(track.getCode().toUpperCase(), track);
        BY_SLUG.put(track.getSlug().toLowerCase(), track);
        ALL.add(track);
    }

    public static CareerTrack get(String code) {
        if (code == null) return null;
        return BY_CODE.get(code.trim().toUpperCase());
    }

    public static CareerTrack fromSlug(String slug) {
        if (slug == null) return null;
        return BY_SLUG.get(slug.trim().toLowerCase());
    }

    public static List<CareerTrack> all() {
        return Collections.unmodifiableList(ALL);
    }
}
