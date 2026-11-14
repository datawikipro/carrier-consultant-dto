package pro.datawiki.carrier.dto.track;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Polymorphic Registry and Factory for CareerTrack taxonomy.
 */
public final class CareerTrackRegistry {

    private static final Map<String, CareerTrack> TRACKS = new LinkedHashMap<>();
    private static final CareerTrack DEFAULT_TRACK;

    static {
        CareerTrack backend = new BackendJavaTrack();
        CareerTrack ds = new DataScienceTrack();
        CareerTrack frontend = new FrontendTrack();
        CareerTrack devops = new DevOpsTrack();
        CareerTrack pm = new ProductManagementTrack();

        TRACKS.put(backend.getTrackId(), backend);
        TRACKS.put(ds.getTrackId(), ds);
        TRACKS.put(frontend.getTrackId(), frontend);
        TRACKS.put(devops.getTrackId(), devops);
        TRACKS.put(pm.getTrackId(), pm);

        DEFAULT_TRACK = backend;
    }

    private CareerTrackRegistry() {}

    public static void register(CareerTrack track) {
        if (track != null && track.getTrackId() != null) {
            TRACKS.put(track.getTrackId(), track);
        }
    }

    public static Optional<CareerTrack> findById(String trackId) {
        if (trackId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(TRACKS.get(trackId.toLowerCase()));
    }

    public static CareerTrack getById(String trackId) {
        return findById(trackId).orElseThrow(() ->
                new IllegalArgumentException("Unknown career track ID: " + trackId));
    }

    public static Collection<CareerTrack> getAllTracks() {
        return Collections.unmodifiableCollection(TRACKS.values());
    }

    public static CareerTrack getDefaultTrack() {
        return DEFAULT_TRACK;
    }

    public static CareerTrack resolveTrackForTitle(String professionTitle) {
        if (professionTitle == null || professionTitle.isBlank()) {
            return DEFAULT_TRACK;
        }
        for (CareerTrack track : TRACKS.values()) {
            if (track.matches(professionTitle)) {
                return track;
            }
        }
        return DEFAULT_TRACK;
    }
}
