package core;

public enum Statuscode_Repo
{
    //Enum class is introduced to ignore hard coding status code individually.
    //WHY INTERVIEWERS LIKE THIS - Clean design/No hard-coding/Enterprise-level enum usage/Easy maintenance
    //step1 define all the status code with message
    success(200, "200 OK – Request successful"),
    created(201, "201 Created – Resource created successfully"),
    no_content(204, "204 No Content – Success, but no response body"),
    bad_request(400, "400 Bad Request – Invalid request / payload"),
    un_authorized(401, "401 Unauthorized – Authentication required or failed"),
    forbidden(403, "403 Forbidden – Access denied"),
    not_found(404, "404 Not Found – Resource not found"),
    methodNot_allow(405, "405 Method Not Allowed – HTTP method not supported"),
    conflict(409, "409 Conflict – Request conflict (duplicate data)"),
    internal_servererror(500, "500 Internal Server Error – Server failure"),
    bad_gateway(502, "502 Bad Gateway – Invalid response from upstream server"),
    service_unavailable(503, "503 Service Unavailable – Server down / overloaded"),
    gateway_timeout(504, "504 Gateway Timeout – Server took too long to respond");
    //step2 define the code and message that we receive from the response body make it final & fixed
    public final int code;
    public final String message;

    //step3 parameterized constructor to call the final & fixed above step2
    Statuscode_Repo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
