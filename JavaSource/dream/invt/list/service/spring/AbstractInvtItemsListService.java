package dream.invt.list.service.spring;

import java.util.List;
import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.service.InvtItemsListService;

public abstract class AbstractInvtItemsListService implements InvtItemsListService {

	protected InvtItemsListService invtItemsListService;
	
    public AbstractInvtItemsListService(InvtItemsListService invtItemsListService)
	{
		this.invtItemsListService = invtItemsListService;
	}
    
    public abstract List findList(InvtCommonDTO invtCommonDTO, User user);
    
    public abstract int deleteList(String[] m_id, User user) throws Exception;

    public abstract String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;
}
