package org.sid.gestionrh.services;
import java.util.List;

public interface CrudServices <RQ,RS,RQU,ID>{
        RS add(RQ request);
        List<RS> get();
        RS get(ID id);
        RS update(RQU request, ID id);
        void delete(ID id);
}
