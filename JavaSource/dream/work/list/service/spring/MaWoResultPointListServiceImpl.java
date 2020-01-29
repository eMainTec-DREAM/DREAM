package dream.work.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.MaWoResultPointListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;
import dream.work.list.dto.MaWoResultPointListDTO;
import dream.work.list.service.MaWoResultPointDetailService;
import dream.work.list.service.MaWoResultPointListService;

/**
 * 작업결과 검사항목 목록
 * @author kim21017
 * @version $Id: MaWoResultPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultPointListServiceTarget"
 * @spring.txbn id="maWoResultPointListService"
 * @spring.property name="maWoResultPointListDAO" ref="maWoResultPointListDAO"
 * @spring.property name="maWoResultPointDetailService" ref="maWoResultPointDetailService"
 */
public class MaWoResultPointListServiceImpl implements MaWoResultPointListService
{
    private MaWoResultPointListDAO maWoResultPointListDAO = null;

    private MaWoResultPointDetailService maWoResultPointDetailService = null;

	public MaWoResultPointListDAO getMaWoResultPointListDAO() {
		return maWoResultPointListDAO;
	}

	public void setMaWoResultPointListDAO(MaWoResultPointListDAO maWoResultPointListDAO) {
		this.maWoResultPointListDAO = maWoResultPointListDAO;
	}
	
	public MaWoResultPointDetailService getMaWoResultPointDetailService() {
		return maWoResultPointDetailService;
	}

	public void setMaWoResultPointDetailService(MaWoResultPointDetailService maWoResultPointDetailService) {
		this.maWoResultPointDetailService = maWoResultPointDetailService;
	}

	public List findPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser)
	{      
		return maWoResultPointListDAO.findPointList(maWoResultMstrCommonDTO,maWoResultPointListDTO, loginUser);
	}
	
	public int deletePointList(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0) {
            List list = new ArrayList();
            MaWoResultPointDetailDTO maWoResultPointDetailDTO = new MaWoResultPointDetailDTO();
            for(String id : deleteRows)
            {
                maWoResultPointDetailDTO.setWoPointId(id);
                list.add(BeanUtils.cloneBean(maWoResultPointDetailDTO));
            }
            result = maWoResultPointListDAO.deletePointList(list, user).length;
        }
        
        return result;
    }

	@Override
	public void savePointList(List<Map> gridList, User user) throws Exception
	{
		MaWoResultPointDetailDTO maWoResultPointDetailDTO = null;
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = null;
        
        for(Map map : gridList)
        {
        	maWoResultPointDetailDTO = (MaWoResultPointDetailDTO)CommonUtil.makeDTO(map, MaWoResultPointDetailDTO.class);
        	maWoResultMstrCommonDTO = (MaWoResultMstrCommonDTO)CommonUtil.makeDTO(map, MaWoResultMstrCommonDTO.class);
        	
        	switch(maWoResultPointDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : maWoResultPointDetailService.updateDetail(maWoResultPointDetailDTO, maWoResultMstrCommonDTO, user);
        			break;
        		case "deleted":
        			break;
        	}
        	
        }
	}

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser) throws Exception 
	{
		return maWoResultPointListDAO.findTotalCount(maWoResultMstrCommonDTO, maWoResultPointListDTO, loginUser);
	}
}

