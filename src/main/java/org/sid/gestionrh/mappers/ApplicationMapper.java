package org.sid.gestionrh.mappers;

import java.util.List;

public interface ApplicationMapper<RQ,RS,ET> {
        ET requestToEntity(RQ employeeRequest);
        RS entityToResponse(ET employeeEntity);
        List<RS> listToResponseList(List<ET> employees);
}
