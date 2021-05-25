package de.tekup.whitetest.endpoint;

import de.tekup.soap.models.whitetest.AllTestsResponse;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;
import de.tekup.whitetest.service.WhiteTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WhiteTestEndPoint {
    public final static String nameSpace = "http://www.tekup.de/soap/models/whitetest";
    @Autowired
    private WhiteTestService service;

    @PayloadRoot(namespace = nameSpace, localPart = "StudentRequest")
    @ResponsePayload
    public WhiteTestResponse whiteTestRes(@RequestPayload StudentRequest studentRequest) {
        return service.getWhiteTestResponse(studentRequest);
    }

    @PayloadRoot(namespace = nameSpace, localPart = "AllTestsRequest")
    @ResponsePayload
    public AllTestsResponse allTestsResponse() {
        return service.getAllTests();
    }

}
