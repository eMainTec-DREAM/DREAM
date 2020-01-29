package dream.asset.categ.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.categ.list.dao.MaEqCtgPartDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;
import dream.asset.categ.list.service.MaEqCtgPartDetailService;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrListService;
import dream.asset.list.service.MaEqMstrPartListService;

/**
 * ���������� ��ǰ
 * @author kim2107
 * @version $Id: MaEqCtgPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCtgPartDetailServiceTarget"
 * @spring.txbn id="maEqCtgPartDetailService"
 * @spring.property name="maEqCtgPartDetailDAO" ref="maEqCtgPartDetailDAO"
 */
public class MaEqCtgPartDetailServiceImpl implements MaEqCtgPartDetailService
{
    private MaEqCtgPartDetailDAO maEqCtgPartDetailDAO = null;
    
    public MaEqCtgPartDetailDAO getMaEqCtgPartDetailDAO() {
		return maEqCtgPartDetailDAO;
	}

	public void setMaEqCtgPartDetailDAO(MaEqCtgPartDetailDAO maEqCtgPartDetailDAO) {
		this.maEqCtgPartDetailDAO = maEqCtgPartDetailDAO;
	}

	public MaEqCtgPartDetailDTO findDetail(MaEqCtgPartListDTO maEqCtgPartListDTO, User user)throws Exception
    {
        return maEqCtgPartDetailDAO.findDetail(maEqCtgPartListDTO, user);
    }
    
	public int updateDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, User user) throws Exception
    {        
		int rtnValue = 0;
		
		MaEqCtgPartListDTO maEqCtgPartListDTO = new MaEqCtgPartListDTO();
		maEqCtgPartListDTO.setEqCtgPartId(maEqCtgPartDetailDTO.getEqCtgPartId());
		MaEqCtgPartDetailDTO originDTO = this.findDetail(maEqCtgPartListDTO, user);
		
		//1. ���������� ��ǰ ���� ó��
		rtnValue =  maEqCtgPartDetailDAO.updateDetail(maEqCtgPartDetailDTO, user);
		
		//2. ������ ���������� �����ǰ  �ϰ� �߰��ϱ�.
		if("Y".equals(maEqCtgPartDetailDTO.getIsEqTypePart())){
		    MaEqMstrListService maEqMstrListService = (MaEqMstrListService) CommonUtil.getBean("maEqMstrListService", user);
		    MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", user);
            
            //�ش� ���������� �����ִ� �����
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEqCtgId(maEqCtgPartDetailDTO.getEqCtgId());
            List<Map> eqList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user);
            for(Map eq:eqList){
                rtnValue += maEqMstrPartListService.inputEqCtgPart(maEqCtgPartDetailDTO, StringUtil.valueOf(eq.get("EQUIPID")), user);
            }
		}
		
		//3. �����ǰ���ΰ� Y->N���� ����� ��� ���� �����ǰ�� �����ǰ���� N
		if("Y".equals(originDTO.getIsEqTypePart()) && "N".equals(maEqCtgPartDetailDTO.getIsEqTypePart())){
		    MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", user);
		    
		    maEqMstrPartListService.undoEqCtgPart(maEqCtgPartDetailDTO.getEqCtgPartId(), user);
		}
		
		return rtnValue;
    }
	
	public int insertDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception
    {        
		int rtnValue = 0;
		
		//1. ���������� ��ǰ ���� ó��
		rtnValue =  maEqCtgPartDetailDAO.insertDetail( maEqCtgPartDetailDTO, maEqCatalogCommonDTO, user);
		
		//2. ������ ���������� �����ǰ �ϰ� �߰��ϱ�.
		if("Y".equals(maEqCtgPartDetailDTO.getIsEqTypePart()))
	    {
		    MaEqMstrListService maEqMstrListService = (MaEqMstrListService) CommonUtil.getBean("maEqMstrListService", user);
            MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", user);
            
            //�ش� ���������� �����ִ� �����
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEqCtgId(maEqCatalogCommonDTO.getEqCtgId());
            List<Map> eqList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user);
            for(Map eq:eqList){
                rtnValue += maEqMstrPartListService.inputEqCtgPart(maEqCtgPartDetailDTO, StringUtil.valueOf(eq.get("EQUIPID")), user);
            }
	    }
		
		return rtnValue;
		
    }
	
	public String copyDetail(String oldEqCtgId, String newEqCtgId, String oldKeyId, String newKeyId, User user) throws Exception
	{
		return maEqCtgPartDetailDAO.copyDetail(oldEqCtgId, newEqCtgId, oldKeyId, newKeyId, user);
	}
}
