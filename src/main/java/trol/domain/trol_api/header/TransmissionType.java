package trol.domain.trol_api.header;

public enum TransmissionType {
    VIDEO("Video"), VIDEO_STREAM("Video stream"), AUDIO("Audio");
    // TODO: Znaleźć możliwe kombinacje headerów dla różnych typów transmisji
    // TODO: Dopisać typy transmisji
    private String name;

    TransmissionType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
