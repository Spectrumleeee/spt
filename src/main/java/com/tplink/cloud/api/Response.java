package com.tplink.cloud.api;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.tplink.cloud.api.trace.ModuleTrace;
import com.tplink.cloud.api.trace.Trace;

public class Response implements Serializable {
    private static final long serialVersionUID = 8861861810300198850L;
    private long id = 0;
    private int errorCode = 0;
    private String result = null;
    private Trace trace = null;

    public Response() {
        this.trace = new Trace();
    }

    public Response(long id) {
        this.id = id;
        this.trace = new Trace();
    }

    public Response(long id, ErrorCode errorCode) {
        this.id = id;
        this.errorCode = errorCode.getCode();
        this.trace = new Trace();
    }

    public Response(long id, ErrorCode errorCode, JSONObject obj) {
        this.id = id;
        this.errorCode = errorCode.getCode();
        this.result = obj.toString();
        this.trace = new Trace();
    }

    public long getId() {
        return id;
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.getErrorCode(errorCode);
    }

    public JSONObject getResult() {
        try {
            if (result != null) {
                return new JSONObject(result);
            }
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
    }

    public void setResult(JSONObject result) {
        if (result != null) {
            this.result = result.toString();
        } else {
            this.result = null;
        }
    }

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("error_code", errorCode);
        if (errorCode != 0) {
            object.put("msg", getErrorCode().getMessage());
        }
        if (result != null) {
            JSONObject resultTemp = getResult();
            if (resultTemp != null) {
                object.put("result", resultTemp);
            } else {
                throw new JSONException("Converse result = " + result + " to JSONObject fail");
            }
        }
        return object;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":" + id + ",");
        sb.append("\"error_code\":" + errorCode);
        if (errorCode != 0) {
            sb.append(",");
            sb.append("\"msg\":\"" + getErrorCode().getMessage() + "\"");
        } else if (result != null) {
            sb.append(",");
            sb.append("\"result\":" + result);
        }
        sb.append("}");
        return sb.toString();
    }

    public void appendModuleTrace(ModuleTrace moduleTrace) {
        // Forward compatibility, for elder backward services.
        if (null == trace) {
            trace = new Trace();
        }
        trace.appendModuleTrace(moduleTrace);
    }

    public void appendSubTrace(Trace nextSubTrace) {
        // Forward compatibility, for elder backward services.
        if (null == trace) {
            trace = new Trace();
        }
        trace.appendSubTrace(nextSubTrace);
    }

    public void appendTrace(Trace nextTrace) {
        // Forward compatibility, for elder backward services.
        if (null == trace) {
            trace = new Trace();
        }
        trace.appendTrace(nextTrace);
    }

    public Trace getTrace() {
        // Forward compatibility, for elder backward services.
        if (null == trace) {
            trace = new Trace();
        }
        return trace;
    }
}