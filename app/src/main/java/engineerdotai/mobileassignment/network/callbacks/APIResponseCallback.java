package engineerdotai.mobileassignment.network.callbacks;

import engineerdotai.mobileassignment.network.RestResponse;

public interface APIResponseCallback<T> {

    void onResponse(RestResponse<T> response);
}
