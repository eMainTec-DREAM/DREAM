package dream.rcm.funceq.service.spring;

import dream.rcm.funceq.dao.RcmFuncEqItemDetailDAO;
import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.service.RcmFuncEqItemDetailService;

/**
 * 답변 - 수신자
 * @author kim2107
 * @version $Id: RcmFuncEqItemDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFuncEqItemDetailServiceTarget"
 * @spring.txbn id="rcmFuncEqItemDetailService"
 * @spring.property name="rcmFuncEqItemDetailDAO" ref="rcmFuncEqItemDetailDAO"
 */
public class RcmFuncEqItemDetailServiceImpl implements RcmFuncEqItemDetailService
{
    private RcmFuncEqItemDetailDAO rcmFuncEqItemDetailDAO = null;
    
    public RcmFuncEqItemDetailDAO getRcmFuncEqItemDetailDAO() {
		return rcmFuncEqItemDetailDAO;
	}

	public void setRcmFuncEqItemDetailDAO(RcmFuncEqItemDetailDAO rcmFuncEqItemDetailDAO) {
		this.rcmFuncEqItemDetailDAO = rcmFuncEqItemDetailDAO;
	}

	public RcmFuncEqItemDetailDTO findDetail(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO)throws Exception
    {
        return rcmFuncEqItemDetailDAO.findDetail(rcmFuncEqItemListDTO, rcmFuncEqCommonDTO);
    }
    
	public int updateDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO) throws Exception
    {        				
		String rcmEq[] = null;

		rcmEq= rcmFuncEqItemDetailDAO.getRcmEq( rcmFuncEqItemDetailDTO,  rcmFuncEqCommonDTO);
		
		//RcmEq 가 있는경우 그대로 입력, 없는경우 RCMEQ 를 추가한후 입력
		if(rcmEq != null)
		{
			rcmFuncEqItemDetailDTO.setRcmEqId(rcmEq[0]);
		}
		else
		{
			String rcmEqId = rcmFuncEqItemDetailDAO.getNextSequence("SQARCMEQ_ID");

			rcmFuncEqItemDetailDTO.setRcmEqId(rcmEqId);
			rcmFuncEqItemDetailDAO.insertRcmEq( rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO);
		}
		
		//부위 데이터가 없는경우 생성
		String rcmAsmb[] = null;
		
		String rcmAsmbId;
		if(!"".equals(rcmFuncEqItemDetailDTO.getAsmbId()))
		{
			rcmAsmb= rcmFuncEqItemDetailDAO.getRcmAsmb( rcmFuncEqItemDetailDTO,  rcmFuncEqCommonDTO);
			//데이터가 없는경우 생성
			if(rcmAsmb==null)
			{
				rcmAsmbId = rcmFuncEqItemDetailDAO.getNextSequence("SQARCMEQ_ID");
				rcmFuncEqItemDetailDTO.setAsmbId(rcmAsmbId);

				rcmFuncEqItemDetailDAO.insertRcmAsmb( rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO,rcmAsmbId);
			}
			else
			{
				rcmFuncEqItemDetailDTO.setAsmbId(rcmAsmb[0]);
			}
		}
		rcmFuncEqItemDetailDAO.updateDetail( rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO);
        return 0;
    }
    
	public int insertDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO) throws Exception
    {        
		String rcmEq[] = null;

		rcmEq= rcmFuncEqItemDetailDAO.getRcmEq( rcmFuncEqItemDetailDTO,  rcmFuncEqCommonDTO);
		
		//RcmEq 가 있는경우 그대로 입력, 없는경우 RCMEQ 를 추가한후 입력
		if(rcmEq != null)
		{
			rcmFuncEqItemDetailDTO.setRcmEqId(rcmEq[0]);
		}
		else
		{
			String rcmEqId = rcmFuncEqItemDetailDAO.getNextSequence("SQARCMEQ_ID");

			rcmFuncEqItemDetailDTO.setRcmEqId(rcmEqId);
			rcmFuncEqItemDetailDAO.insertRcmEq( rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO);
		}
		
		//부위 데이터가 없는경우 생성
		String rcmAsmb[] = null;
		
		String rcmAsmbId;
		if(!"".equals(rcmFuncEqItemDetailDTO.getAsmbId()))
		{
			rcmAsmb= rcmFuncEqItemDetailDAO.getRcmAsmb( rcmFuncEqItemDetailDTO,  rcmFuncEqCommonDTO);
			//데이터가 없는경우 생성
			if(rcmAsmb==null)
			{
				rcmAsmbId = rcmFuncEqItemDetailDAO.getNextSequence("SQARCMEQ_ID");
				
				rcmFuncEqItemDetailDAO.insertRcmAsmb( rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO,rcmAsmbId);
				rcmFuncEqItemDetailDTO.setAsmbId(rcmAsmbId);
			}
			else
			{
				rcmFuncEqItemDetailDTO.setAsmbId(rcmAsmb[0]);
			}
		}
		rcmFuncEqItemDetailDAO.insertDetail( rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO);
        return 0;
    }
}
