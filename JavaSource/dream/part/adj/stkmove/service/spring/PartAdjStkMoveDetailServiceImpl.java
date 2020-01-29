package dream.part.adj.stkmove.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.part.adj.stkmove.dao.PartAdjStkMoveDetailDAO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveDetailDTO;
import dream.part.adj.stkmove.service.PartAdjStkMoveDetailService;

/**
 * 재고이동 - 상세 serviceimpl 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partAdjStkMoveDetailServiceTarget"
 * @spring.txbn id="partAdjStkMoveDetailService"
 * @spring.property name="partAdjStkMoveDetailDAO" ref="partAdjStkMoveDetailDAO"
 */
public class PartAdjStkMoveDetailServiceImpl implements PartAdjStkMoveDetailService
{
    private PartAdjStkMoveDetailDAO partAdjStkMoveDetailDAO = null;
    
    public PartAdjStkMoveDetailDAO getPartAdjStkMoveDetailDAO() {
		return partAdjStkMoveDetailDAO;
	}

	public void setPartAdjStkMoveDetailDAO(PartAdjStkMoveDetailDAO partAdjStkMoveDetailDAO) {
		this.partAdjStkMoveDetailDAO = partAdjStkMoveDetailDAO;
	}

	public PartAdjStkMoveDetailDTO findDetail(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user)throws Exception
    {
        return partAdjStkMoveDetailDAO.findDetail(partAdjStkMoveCommonDTO, user);
    }
	
	public int insertDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
	{        
	    return partAdjStkMoveDetailDAO.insertDetail(partAdjStkMoveDetailDTO, user);
	}
	
	public int updateDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
    {        
        return partAdjStkMoveDetailDAO.updateDetail(partAdjStkMoveDetailDTO, user);
    }
	
	public String[] moveComp(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
    {
	    //출고처리
        if(!"".equals(partAdjStkMoveDetailDTO.getFromWcodeId())){
            String ptIssHistId = partAdjStkMoveDetailDAO.getNextSequence("SQAPTISSHIST_ID");
            String issMode = "C";
            partAdjStkMoveDetailDAO.insertPtIssHistory(partAdjStkMoveDetailDTO, ptIssHistId, issMode, user);
            partAdjStkMoveDetailDAO.executeSpPtOutStock(ptIssHistId, user);
        }
        //입고처리
        if(!"".equals(partAdjStkMoveDetailDTO.getToWcodeId())){
            String ptRecHistId = partAdjStkMoveDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            String recMode = "C";
            partAdjStkMoveDetailDAO.insertPtRecHistory(partAdjStkMoveDetailDTO, ptRecHistId, recMode, user);
            partAdjStkMoveDetailDAO.executeSpPtInStock(ptRecHistId, user);
        }
        //완료처리
        partAdjStkMoveDetailDAO.confirmDetail(partAdjStkMoveDetailDTO, user);
        
        return new String[]{"S",""};
    }
	
	public String[] moveCancel(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
	{
	    //출고취소
        if(!"".equals(partAdjStkMoveDetailDTO.getFromWcodeId())){
            String ptIssHistId = partAdjStkMoveDetailDAO.getNextSequence("SQAPTISSHIST_ID");
            String issMode = "R";
            partAdjStkMoveDetailDAO.insertPtIssHistory(partAdjStkMoveDetailDTO, ptIssHistId, issMode, user);
            partAdjStkMoveDetailDAO.executeSpPtOutStock(ptIssHistId, user);
        }
        //입고취소
        if(!"".equals(partAdjStkMoveDetailDTO.getToWcodeId())){
            String ptRecHistId = partAdjStkMoveDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            String recMode = "R";
            partAdjStkMoveDetailDAO.insertPtRecHistory(partAdjStkMoveDetailDTO, ptRecHistId, recMode, user);
            partAdjStkMoveDetailDAO.executeSpPtInStock(ptRecHistId, user);
        }
        //취소처리
        partAdjStkMoveDetailDAO.cancelDetail(partAdjStkMoveDetailDTO, user);
        
        return new String[]{"S",""};
	}
}
