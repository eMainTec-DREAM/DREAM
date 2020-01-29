package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.MaWoResultCraftListDAO;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultCraftDetailService;
import dream.work.list.service.MaWoResultCraftListService;
import dream.work.list.service.MaWoResultMstrDetailService;

/**
 * 작업결과 작업자 목록
 * @author kim21017
 * @version $Id: MaWoResultCraftListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultCraftListServiceTarget"
 * @spring.txbn id="maWoResultCraftListService"
 * @spring.property name="maWoResultCraftListDAO" ref="maWoResultCraftListDAO"
 * @spring.property name="maWoResultCraftDetailService" ref="maWoResultCraftDetailService"
 */
public class MaWoResultCraftListServiceImpl implements MaWoResultCraftListService
{
    private MaWoResultCraftListDAO maWoResultCraftListDAO = null;
    private MaWoResultCraftDetailService maWoResultCraftDetailService = null;


	public MaWoResultCraftDetailService getMaWoResultCraftDetailService()
    {
        return maWoResultCraftDetailService;
    }

    public void setMaWoResultCraftDetailService(
            MaWoResultCraftDetailService maWoResultCraftDetailService)
    {
        this.maWoResultCraftDetailService = maWoResultCraftDetailService;
    }

    public List findCraftList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser)
    {      
        return maWoResultCraftListDAO.findCraftList(maWoResultMstrCommonDTO, maWoResultCraftListDTO, loginUser);
    }

	public MaWoResultCraftListDAO getMaWoResultCraftListDAO() {
		return maWoResultCraftListDAO;
	}

	public void setMaWoResultCraftListDAO(MaWoResultCraftListDAO maWoResultCraftListDAO) {
		this.maWoResultCraftListDAO = maWoResultCraftListDAO;
	}
	
	public int deleteCraftList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoResultCraftListDAO.deleteCraftList(id, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			MaWoResultCraftListDTO maWoResultCraftListDTO, User user) throws Exception {
		return maWoResultCraftListDAO.findTotalCount(maWoResultMstrCommonDTO, maWoResultCraftListDTO, user);
	}

    @Override
    public int inputCraftList(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maWoResultCraftDetailDTO.getMultiKey().split("\\+");
        
        MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", user);
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
        
        // 상세 페이지의 작업 시작일자/시간, 종료일자/시간 셋팅
        maWoResultCraftDetailDTO.setStartDate(maWoResultMstrDetailDTO.getStartDate());
        maWoResultCraftDetailDTO.setStartTime(maWoResultMstrDetailDTO.getStartTime());
        maWoResultCraftDetailDTO.setEndDate(maWoResultMstrDetailDTO.getEndDate());
        maWoResultCraftDetailDTO.setEndTime(maWoResultMstrDetailDTO.getEndTime());
        maWoResultCraftDetailDTO.setWorkTime(maWoResultMstrDetailDTO.getWorkTime());
        
        for(int i=0; multiKey.length > i; i++)
        {
        	maWoResultCraftDetailDTO.setEmpId(multiKey[i]);
            String cnt = maWoResultCraftDetailService.validEmp(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO, user);
            if("0".equals(cnt))
            {
                maWoResultCraftDetailDTO.setWoCraftId(maWoResultCraftListDAO.getNextSequence("SQAWOCRAFT_ID"));
                result = result + maWoResultCraftDetailService.insertDetail(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO);
            }
        }
        
        return result;
    }
}

