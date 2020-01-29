package dream.invt.list.service.spring;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtItemsDetailDTO;
import dream.invt.list.service.InvtItemsDetailService;

public abstract class AbstractInvtItemsDetailService implements InvtItemsDetailService {

	protected InvtItemsDetailService invtItemsDetailService;
	
    public AbstractInvtItemsDetailService(InvtItemsDetailService invtItemsDetailService)
	{
		this.invtItemsDetailService = invtItemsDetailService;
	}
    
    public abstract InvtItemsDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception;

    public abstract int updateDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public abstract int insertDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public abstract String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception;
}
