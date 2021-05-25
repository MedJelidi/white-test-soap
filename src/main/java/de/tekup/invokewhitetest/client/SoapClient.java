package de.tekup.invokewhitetest.client;

import de.tekup.invokewhitetest.soap.api.consume.whitetest.AllTestsResponse;
import de.tekup.invokewhitetest.soap.api.consume.whitetest.StudentRequest;
import de.tekup.invokewhitetest.soap.api.consume.whitetest.WhiteTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {
    private WebServiceTemplate serviceTemplate;
    @Autowired
    private Jaxb2Marshaller marshaller;

    public WhiteTestResponse getWhiteTestResp(StudentRequest studentRequest) {
        serviceTemplate = new WebServiceTemplate(marshaller);
        return (WhiteTestResponse) serviceTemplate
                .marshalSendAndReceive("http://localhost:8080/ws", studentRequest);
    }

    public AllTestsResponse getAllExams(Object object) {
        serviceTemplate = new WebServiceTemplate(marshaller);
        return (AllTestsResponse) serviceTemplate
                .marshalSendAndReceive("http://localhost:8080/ws", object);
    }
}
