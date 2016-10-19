/**
 * Copyright 2014 Ruckus Wireless, Inc. All rights reserved.
 * <p/>
 * RUCKUS WIRELESS, INC. CONFIDENTIAL -
 * This is an unpublished, proprietary work of Ruckus Wireless, Inc., and is
 * fully protected under copyright and trade secret laws. You may not view,
 * use, disclose, copy, or distribute this file or any information contained
 * herein except pursuant to a valid license from Ruckus.
 */

package com.mobile.android.weather.parser;

import org.json.JSONObject;

/**
 *
 *
 */
public interface JSONParser<T> {
    public T parse(JSONObject jsonObj);
}
