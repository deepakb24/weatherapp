/**
 *
 */
package com.mobile.android.weather.network;

import com.loopj.android.http.RequestParams;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * Base class for all network requests.
 */
abstract class Request {

    protected String baseURL;
    private int requestID;
    private boolean isCanceled;

    private String responseType;

    public Request() {
    }

    /**
     * Returns whether current request is canceled or not
     *
     * @return whether current request is canceled or not
     */
    public boolean isCanceled() {
        return isCanceled;
    }

    /**
     * Cancel the request
     *
     * @param isCanceled
     */
    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    /**
     * Method getPostData.
     *
     * @return List<NameValuePair>
     */
    public abstract List<NameValuePair> getPostData();

    /**
     * Method getHttpParams.
     *
     * @return HttpParams
     */
    public abstract RequestParams getRequestParams();

    public abstract Map<String, String> getHttpHeaders();

    /**
     * Method getUrl.
     *
     * @return String
     */
    public abstract String getUrl();

    /**
     * Get request body
     *
     * @return String
     */
    public abstract String getRequestBody();

    /**
     * Method getRequestID.
     *
     * @return int
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Method setRequestID.
     *
     * @param requestID int
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Request [requestID=" + requestID + "]";
    }

}
