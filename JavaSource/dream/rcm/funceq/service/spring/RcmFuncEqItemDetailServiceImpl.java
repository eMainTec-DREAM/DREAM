package dream.rcm.funceq.service.spring;

import dream.rcm.funceq.dao.RcmFuncEqItemDetailDAO;
import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.service.RcmFuncEqItemDetailService;

/**
 * �亯 - ������
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
		
		//RcmEq �� �ִ°�� �״�� �Է�, ���°�� RCMEQ �� �߰����� �Է�
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
		
		//���� �����Ͱ� ���°�� ����
		String rcmAsmb[] = null;
		
		String rcmAsmbId;
		if(!"".equals(rcmFuncEqItemDetailDTO.getAsmbId()))
		{
			rcmAsmb= rcmFuncEqItemDetailDAO.getRcmAsmb( rcmFuncEqItemDetailDTO,  rcmFuncEqCommonDTO);
			//�����Ͱ� ���°�� ����
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
		
		//RcmEq �� �ִ°�� �״�� �Է�, ���°�� RCMEQ �� �߰����� �Է�
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
		
		//���� �����Ͱ� ���°�� ����
		String rcmAsmb[] = null;
		
		String rcmAsmbId;
		if(!"".equals(rcmFuncEqItemDetailDTO.getAsmbId()))
		{
			rcmAsmb= rcmFuncEqItemDetailDAO.getRcmAsmb( rcmFuncEqItemDetailDTO,  rcmFuncEqCommonDTO);
			//�����Ͱ� ���°�� ����
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
