package dream.rcm.funceq.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dao.RcmFuncEqItemListDAO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.service.RcmFuncEqItemListService;

/**
 * 답변 목록 serviceimpl
 * @author kim21017
 * @version $Id: RcmFuncEqItemListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFuncEqItemListServiceTarget"
 * @spring.txbn id="rcmFuncEqItemListService"
 * @spring.property name="rcmFuncEqItemListDAO" ref="rcmFuncEqItemListDAO"
 */
public class RcmFuncEqItemListServiceImpl implements RcmFuncEqItemListService
{
    private RcmFuncEqItemListDAO rcmFuncEqItemListDAO = null;

    public RcmFuncEqItemListDAO getRcmFuncEqItemListDAO() {
		return rcmFuncEqItemListDAO;
	}
	public void setRcmFuncEqItemListDAO(RcmFuncEqItemListDAO rcmFuncEqItemListDAO) {
		this.rcmFuncEqItemListDAO = rcmFuncEqItemListDAO;
	}
	
	public List findItemList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO)
    {      
        return rcmFuncEqItemListDAO.findItemList(rcmFuncEqCommonDTO, rcmFuncEqItemListDTO);
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmFuncEqItemListDAO.deleteItemList(deleteRows[i]);
        }
        
        return result;
    }
	@Override
	public int inputList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO) {
		int result = 0;
		
		String[] multiEquip = rcmFuncEqItemListDTO.getMultiKey().split("\\+");
		String[] multiAsmb = rcmFuncEqItemListDTO.getMultiDesc().split("\\+");


		for(int i=0; multiEquip.length > i; i++)
		{
			multiAsmb[i]= multiAsmb[i].replace("null", "");
			String rcmEq = "";
			String rcmAsmbId = null;
			rcmEq= rcmFuncEqItemListDAO.getEquip(rcmFuncEqCommonDTO,  rcmFuncEqItemListDTO, multiEquip[i],multiAsmb[i]);
			String rcmEqId = "";
			
			if("".equals(rcmEq)||rcmEq==null)
			{
				 rcmEqId = rcmFuncEqItemListDAO.getNextSequence("SQARCMEQ_ID");
				 rcmEq= rcmEqId;
				rcmFuncEqItemListDAO.insertRcmEq( rcmFuncEqItemListDTO, rcmFuncEqCommonDTO,rcmEqId,multiEquip[i]);	
			}
			
			//부위 아이디가 있다면 실행한다.
			if(!"".equals(multiAsmb[i]))
			{
				String rcmAsmb[] = null;
				rcmAsmb= rcmFuncEqItemListDAO.getRcmAsmb(rcmFuncEqCommonDTO,  rcmFuncEqItemListDTO, rcmEq,multiAsmb[i]);
				//데이터가 없는경우 생성
				if(rcmAsmb==null)
				{					
					rcmAsmbId = rcmFuncEqItemListDAO.getNextSequence("SQARCMEQ_ID");
					//multiAsmb[i]= rcmAsmbId;

					rcmFuncEqItemListDAO.insertRcmAsmb( rcmFuncEqItemListDTO, rcmFuncEqCommonDTO,rcmAsmbId,rcmEq,multiAsmb[i]);
				}
				else
				{
					rcmAsmbId=rcmAsmb[0];
				}
			}
			
			
			rcmFuncEqItemListDAO.insertDetail(rcmFuncEqItemListDTO, rcmFuncEqCommonDTO,rcmEq,rcmAsmbId);
		}		
		return result;
	}
	@Override
	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			User user) {
		return rcmFuncEqItemListDAO.findTotalCount(rcmFuncEqCommonDTO, rcmFuncEqItemListDTO, user);
	}
}

