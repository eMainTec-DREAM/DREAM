package dream.work.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.MaWoResultPartListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.MaWoResultPartListDTO;
import dream.work.list.service.MaWoResultPartDetailService;
import dream.work.list.service.MaWoResultPartListService;

/**
 * 작업결과 투입자재 목록
 * @author kim21017
 * @version $Id: MaWoResultPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultPartListServiceTarget"
 * @spring.txbn id="maWoResultPartListService"
 * @spring.property name="maWoResultPartListDAO" ref="maWoResultPartListDAO"
 * @spring.property name="maWoResultPartDetailService" ref="maWoResultPartDetailService"
 */
public class MaWoResultPartListServiceImpl implements MaWoResultPartListService
{
    private MaWoResultPartListDAO maWoResultPartListDAO = null;
    private MaWoResultPartDetailService maWoResultPartDetailService = null;


	public MaWoResultPartDetailService getMaWoResultPartDetailService()
    {
        return maWoResultPartDetailService;
    }

    public void setMaWoResultPartDetailService(
            MaWoResultPartDetailService maWoResultPartDetailService)
    {
        this.maWoResultPartDetailService = maWoResultPartDetailService;
    }

    public List findPartList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User loginUser)
    {      
        return maWoResultPartListDAO.findPartList(maWoResultMstrCommonDTO, maWoResultPartListDTO, loginUser);
    }

	public MaWoResultPartListDAO getMaWoResultPartListDAO() {
		return maWoResultPartListDAO;
	}

	public void setMaWoResultPartListDAO(MaWoResultPartListDAO maWoResultPartListDAO) {
		this.maWoResultPartListDAO = maWoResultPartListDAO;
	}
	
	public int deletePartList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String woPartId : deleteRows)
            {
            	maWoResultPartListDAO.updateEmgPart(woPartId, compNo);
            	
                result = result + maWoResultPartListDAO.deletePartList(woPartId, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			MaWoResultPartListDTO maWoResultPartListDTO, User user) throws Exception {
		return maWoResultPartListDAO.findTotalCount(maWoResultMstrCommonDTO, maWoResultPartListDTO, user);
	}
	
	@Override
    public int inputPartList(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maWoResultPartDetailDTO.getMultiPartKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maWoResultPartDetailDTO.setWoPartId(maWoResultPartListDAO.getNextSequence("SQAWOPART_ID"));
            maWoResultPartDetailDTO.setPartId(multiKey[i]);
            result = result + maWoResultPartDetailService.insertDetail(maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
        }
        
        return result;
    }

    @Override
    public int inputIssPartList(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maWoResultPartDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maWoResultPartDetailDTO.setWoPartId(maWoResultPartListDAO.getNextSequence("SQAWOPART_ID"));
            maWoResultPartDetailDTO.setPtEmgIssListId(multiKey[i]);
            result = result + maWoResultPartDetailService.insertIssPartDetail(maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
            maWoResultPartDetailService.updateEmgPart(maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
        }
        
        return result;
    }
    
    @Override
    public int inputPtIssList(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maWoResultPartDetailDTO.getMultiIssKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maWoResultPartDetailDTO.setWoPartId(maWoResultPartListDAO.getNextSequence("SQAWOPART_ID"));
            maWoResultPartDetailDTO.setPtisslistId(multiKey[i]);
            result = result + maWoResultPartDetailService.insertPtIssDetail(maWoResultPartDetailDTO, user);
        }
        
        return result;
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception
	{
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = null;
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = null;
		
		for(Map map : gridList)
        {
			maWoResultMstrCommonDTO = (MaWoResultMstrCommonDTO)CommonUtil.makeDTO(map, MaWoResultMstrCommonDTO.class);
			maWoResultPartDetailDTO = (MaWoResultPartDetailDTO)CommonUtil.makeDTO(map, MaWoResultPartDetailDTO.class);
			
        	switch(maWoResultPartDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : maWoResultPartDetailService.updateDetail(maWoResultPartDetailDTO,maWoResultMstrCommonDTO, user);
        			break;
        		case "deleted": 
        			break;
        	}
        	
        }
	}
}

