package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.form.LovPartsListForm;
import dream.part.list.service.LovPartsListService;

public abstract class AbstractLovPartsListService implements LovPartsListService
{
    protected LovPartsListService lovPartsListService;
    
    public AbstractLovPartsListService(LovPartsListService lovPartsListService)
    {
        this.lovPartsListService = lovPartsListService;
    }
    
    public abstract List findPartsAcList(LovPartsListForm lovPartsListForm, User loginUser);
    public abstract String findTotalCount(LovPartsListForm lovPartsListForm, User user) throws Exception;
}