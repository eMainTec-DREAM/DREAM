package dream.asset.categ.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.categ.list.dao.MaEqCtgSpecDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;
import dream.asset.categ.list.service.MaEqCtgSpecDetailService;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrListService;
import dream.asset.list.service.MaEqMstrSpecListService;

/**
 * ���������� ǥ������
 * @author syyang
 * @version $Id: MaEqCtgSpecDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCtgSpecDetailServiceTarget"
 * @spring.txbn id="maEqCtgSpecDetailService"
 * @spring.property name="maEqCtgSpecDetailDAO" ref="maEqCtgSpecDetailDAO"
 */
public class MaEqCtgSpecDetailServiceImpl implements MaEqCtgSpecDetailService
{
    private MaEqCtgSpecDetailDAO maEqCtgSpecDetailDAO = null;
    
    public MaEqCtgSpecDetailDAO getMaEqCtgSpecDetailDAO() {
		return maEqCtgSpecDetailDAO;
	}

	public void setMaEqCtgSpecDetailDAO(MaEqCtgSpecDetailDAO maEqCtgSpecDetailDAO) {
		this.maEqCtgSpecDetailDAO = maEqCtgSpecDetailDAO;
	}

	public MaEqCtgSpecDetailDTO findDetail(MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user)throws Exception
    {
        return maEqCtgSpecDetailDAO.findDetail(maEqCtgSpecListDTO, user);
    }
    
	public int updateDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, User user) throws Exception
    {        
        int rtnValue = 0;
        
        MaEqCtgSpecListDTO maEqCtgSpecListDTO = new MaEqCtgSpecListDTO();
        maEqCtgSpecListDTO.setEqCtgSpecId(maEqCtgSpecDetailDTO.getEqCtgSpecId());
        MaEqCtgSpecDetailDTO originDTO = this.findDetail(maEqCtgSpecListDTO, user);
        
        //1. ���������� ���� ���� ó��
		rtnValue =  maEqCtgSpecDetailDAO.updateDetail(maEqCtgSpecDetailDTO, user);
		//2. ������ ���������� �������� �ϰ� �߰��ϱ�.
		if("Y".equals(maEqCtgSpecDetailDTO.getIsEqTypeSpec())){
			MaEqMstrListService maEqMstrListService = (MaEqMstrListService) CommonUtil.getBean("maEqMstrListService", user);
			MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", user);
            
            //�ش� ���������� �����ִ� �����
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEqCtgId(maEqCtgSpecDetailDTO.getEqCtgId());
            List<Map> eqList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user);
            for(Map eq:eqList){
                rtnValue += maEqMstrSpecListService.inputEqCtgSpecList(maEqCtgSpecDetailDTO, StringUtil.valueOf(eq.get("EQUIPID")), user);
            }
		}
		//3. �����������ΰ� Y->N���� ����� ��� ���� ���������� ������������ N
		if("Y".equals(originDTO.getIsEqTypeSpec()) && "N".equals(maEqCtgSpecDetailDTO.getIsEqTypeSpec())){
		    MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", user);
		    
		    maEqMstrSpecListService.undoEqCtgSpec(maEqCtgSpecDetailDTO.getEqCtgSpecId(), user);
		}
		
		return rtnValue;
    }
	
	public int insertDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception
    {        
		int rtnValue = 0;
		
		//1. ���������� ���� ���� ó��
		rtnValue =  maEqCtgSpecDetailDAO.insertDetail(maEqCtgSpecDetailDTO, maEqCatalogCommonDTO, user);
		//2. ������ ���������� ��������  �ϰ� �߰��ϱ�.
		if("Y".equals(maEqCtgSpecDetailDTO.getIsEqTypeSpec())){
			MaEqMstrListService maEqMstrListService = (MaEqMstrListService) CommonUtil.getBean("maEqMstrListService", user);
            MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", user);
            
            //�ش� ���������� �����ִ� �����
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEqCtgId(maEqCatalogCommonDTO.getEqCtgId());
            List<Map> eqList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user);
            for(Map eq:eqList){
                rtnValue += maEqMstrSpecListService.inputEqCtgSpecList(maEqCtgSpecDetailDTO, StringUtil.valueOf(eq.get("EQUIPID")), user);
            }
		}
		
		return rtnValue;
    }
}
