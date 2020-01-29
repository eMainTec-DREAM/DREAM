package dream.asset.categ.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.categ.list.dao.MaEqCatalogDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;
import dream.asset.categ.list.service.MaEqCatalogDetailService;
import dream.asset.categ.list.service.MaEqCatalogListService;

/**
 * 설비종류 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaEqCatalogDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqCatalogDetailServiceTarget"
 * @spring.txbn id="maEqCatalogDetailService"
 * @spring.property name="maEqCatalogDetailDAO" ref="maEqCatalogDetailDAO"
 */
public class MaEqCatalogDetailServiceImpl implements MaEqCatalogDetailService
{
    private MaEqCatalogDetailDAO maEqCatalogDetailDAO = null;
    
    public MaEqCatalogDetailDAO getMaEqCatalogDetailDAO() {
		return maEqCatalogDetailDAO;
	}

	public void setMaEqCatalogDetailDAO(MaEqCatalogDetailDAO maEqCatalogDetailDAO) {
		this.maEqCatalogDetailDAO = maEqCatalogDetailDAO;
	}

	public MaEqCatalogDetailDTO findDetail(String eqCtgId,User loginUser)throws Exception
    {
        return maEqCatalogDetailDAO.findDetail(eqCtgId,loginUser);
    }
	
	public int insertDetail(MaEqCatalogDetailDTO maEqCatalogDetailDTO, User user) throws Exception
    {        
	    int rtnValue = 0;
	    
	    rtnValue = maEqCatalogDetailDAO.insertDetail(maEqCatalogDetailDTO, user);
	    
	    //full_desc를 update
	    this.updateFullDesc(maEqCatalogDetailDTO.getEqCtgId(), user);
	    
        return rtnValue;
    }
	
	public int updateDetail(MaEqCatalogDetailDTO maEqCatalogDetailDTO, User user) throws Exception
    {        
	    int rtnValue = 0;
	    
	    //update 전 기존 데이터 find(description 또는 부모가 변경되었는지 확인용)
	    MaEqCatalogDetailDTO originDTO = this.findDetail(maEqCatalogDetailDTO.getEqCtgId(), user);
        
	    List list = new ArrayList();
	    list.add(maEqCatalogDetailDTO);
	    rtnValue = maEqCatalogDetailDAO.updateDetail(list, user)[0];
        
	    //description이 변경되었거나 부모가 변경되었을 경우 full_desc를 update
        if(!maEqCatalogDetailDTO.getEqCtgDesc().equals(originDTO.getEqCtgDesc())
                || !maEqCatalogDetailDTO.getPeqCtgId().equals(originDTO.getPeqCtgId())){
            this.updateFullDesc(maEqCatalogDetailDTO.getEqCtgId(), user);
        }
        
        return rtnValue;
    }

    private int updateFullDesc(String eqCtgId, User user) throws Exception
    {
        int rtnValue = 0;
        
        MaEqCatalogListService maEqCatalogListService = (MaEqCatalogListService) CommonUtil.getBean("maEqCatalogListService", user);
        List list = CommonUtil.makeFullDesc(maEqCatalogListService.findEqCatalogList(new MaEqCatalogCommonDTO(), user), eqCtgId, "EQCTGID", "PEQCTGID", "EQCTGDESC", "FULLDESC", MaEqCatalogDetailDTO.class);
        
        int[] arr = maEqCatalogDetailDAO.updateDetail(list, user);
        
        return rtnValue;
    }

    @Override
    public int updateEqCtgFullDesc(User user) throws Exception
    {
        int rtnValue = 0;
        
        MaEqCatalogListService maEqCatalogListService = (MaEqCatalogListService) CommonUtil.getBean("maEqCatalogListService", user);
        List list = CommonUtil.makeFullDesc(maEqCatalogListService.findEqCatalogList(new MaEqCatalogCommonDTO(), user), "0", "EQCTGID", "PEQCTGID", "EQCTGDESC", "FULLDESC", MaEqCatalogDetailDTO.class);
        
        int[] arr = maEqCatalogDetailDAO.updateDetail(list, user);
        
        return rtnValue;
    }
}
