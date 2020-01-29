package dream.work.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.list.dao.MaWoResultPartDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.service.MaWoResultPartDetailService;

/**
 * 작업결과 투입자재
 * @author kim2107
 * @version $Id: MaWoResultPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultPartDetailServiceTarget"
 * @spring.txbn id="maWoResultPartDetailService"
 * @spring.property name="maWoResultPartDetailDAO" ref="maWoResultPartDetailDAO"
 */
public class MaWoResultPartDetailServiceImpl implements MaWoResultPartDetailService
{
    private MaWoResultPartDetailDAO maWoResultPartDetailDAO = null;
    
    public MaWoResultPartDetailDAO getMaWoResultPartDetailDAO() {
		return maWoResultPartDetailDAO;
	}

	public void setMaWoResultPartDetailDAO(MaWoResultPartDetailDAO maWoResultPartDetailDAO) {
		this.maWoResultPartDetailDAO = maWoResultPartDetailDAO;
	}

	public MaWoResultPartDetailDTO findDetail(String wkOrId, String woPartId, User user)throws Exception
    {
        return maWoResultPartDetailDAO.findDetail(wkOrId, woPartId, user);
    }
    
	public int updateDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception
    {        
		
        return maWoResultPartDetailDAO.updateDetail(maWoResultPartDetailDTO, maWoResultMstrCommonDTO,user);
    }
	public int insertDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {   		
		
		//Serial Parts 아니면 Serial 삭제 
		
        return maWoResultPartDetailDAO.insertDetail( maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
    }
	public String getStockQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser){
		return maWoResultPartDetailDAO.getStockQty(maWoResultPartDetailDTO, loginUser);
	}
	
	public String checkQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser){
	    String valid = "S";
	    
	    List IssQtyList = maWoResultPartDetailDAO.getIssQty(maWoResultPartDetailDTO, loginUser);
	    
	    Map qtyMap = (Map) IssQtyList.get(0);
	    
	    int issueQty = Integer.parseInt(maWoResultPartDetailDAO.convertString(qtyMap.get("issueQty")));
	    int totalUseQty = Integer.parseInt(maWoResultPartDetailDAO.convertString(qtyMap.get("useQty"))) 
	            + Integer.parseInt(maWoResultPartDetailDTO.getUseQty());
	    
	    if(totalUseQty>issueQty) {
	        valid = "E";
	    }
	    
	    return valid;
	}

    @Override
    public int insertIssPartDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {
        return maWoResultPartDetailDAO.insertIssPartDetail(maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
    }

    @Override
    public int updateEmgPart(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {
        return maWoResultPartDetailDAO.updateEmgPart(maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
    }

    @Override
    public int insertPtIssDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception
    {
        return maWoResultPartDetailDAO.insertPtIssDetail(maWoResultPartDetailDTO, user);
    }
}
