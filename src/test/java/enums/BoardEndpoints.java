package enums;

public enum BoardEndpoints {
    CREATE_BOARD("/boards/"),
    GET_BOARD("/boards/{id}"),
    UPDATE_BOARD("/boards/{id}"),
    DELETE_BOARD("/boards/{id}");

    private final String endpoint;

    BoardEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
