package dream.asset.categ.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.categ.list.dao.MaEqCtgAsmbDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.asset.categ.list.service.MaEqCtgAsmbDetailService;
import dream.asset.categ.list.service.MaEqCtgAsmbListService;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.asset.list.service.MaEqMstrListService;

/**
 * ���������� �۾�����
 * @author kim2107
 * @version $Id: MaEqCtgAsmbDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCtgAsmbDetailServiceTarget"
 * @spring.txbn id="maEqCtgAsmbDetailService"
 * @spring.property name="maEqCtgAsmbDetailDAO" ref="maEqCtgAsmbDetailDAO"
 */
public class MaEqCtgAsmbDetailServiceImpl implements MaEqCtgAsmbDetailService
{
    private MaEqCtgAsmbDetailDAO maEqCtgAsmbDetailDAO = null;
    
    public MaEqCtgAsmbDetailDAO getMaEqCtgAsmbDetailDAO() {
		return maEqCtgAsmbDetailDAO;
	}

	public void setMaEqCtgAsmbDetailDAO(MaEqCtgAsmbDetailDAO maEqCtgAsmbDetailDAO) {
		this.maEqCtgAsmbDetailDAO = maEqCtgAsmbDetailDAO;
	}

	public MaEqCtgAsmbDetailDTO findDetail(MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user)throws Exception
    {
        return maEqCtgAsmbDetailDAO.findDetail(maEqCtgAsmbListDTO, user);
    }
    
	public int updateDetail(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, User user) throws Exception
    {        
        int rtnValue = 0;
        
        //update �� ���� ������ find(description �Ǵ� �θ� ����Ǿ����� Ȯ�ο�)
        MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = new MaEqCtgAsmbListDTO();
        maEqCtgAsmbListDTO.setEqCtgAsmbId(maEqCtgAsmbDetailDTO.getEqCtgAsmbId());
        MaEqCtgAsmbDetailDTO originDTO = this.findDetail(maEqCtgAsmbListDTO, user);
        
        //1. ���������� ���� ���� ó��
        List list = new ArrayList();
        list.add(maEqCtgAsmbDetailDTO);
		rtnValue =  maEqCtgAsmbDetailDAO.updateDetail(list, user)[0];
		
		//description�� ����Ǿ��ų� �θ� ����Ǿ��� ��� full_desc�� update
		if(!maEqCtgAsmbDetailDTO.getEqCtgAsmbDesc().equals(originDTO.getEqCtgAsmbDesc())
		        || !maEqCtgAsmbDetailDTO.getPeqCtgAsmbId().equals(originDTO.getPeqCtgAsmbId())){
		    this.updateFullDesc(maEqCtgAsmbDetailDTO, user);
		}
		
		//2. ������ ���������� ������� �ϰ� �߰��ϱ�.
		if("Y".equals(maEqCtgAsmbDetailDTO.getIsEqTypeAsmb()))
        {
		    MaEqMstrListService maEqMstrListService = (MaEqMstrListService) CommonUtil.getBean("maEqMstrListService", user);
		    MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
		    
		    //�ش� ���������� �����ִ� �����(�����Ѽ��� ����)
		    MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
		    maEqMstrCommonDTO.setEqCtgId(maEqCtgAsmbDetailDTO.getEqCtgId());
		    List<Map> eqList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user);
		    for(Map eq:eqList){
		        rtnValue += maEqMstrAsmbListService.inputAsmb(maEqCtgAsmbDetailDTO, StringUtil.valueOf(eq.get("EQUIPID")), user);
		    }
        }
		
		//3. ����������ΰ� Y->N���� ����� ��� ���� ��������� ����������� N
		if("Y".equals(originDTO.getIsEqTypeAsmb()) && "N".equals(maEqCtgAsmbDetailDTO.getIsEqTypeAsmb()))
		{
		    MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
            
		    maEqMstrAsmbListService.undoEqCtgAsmb(maEqCtgAsmbDetailDTO.getEqCtgAsmbId(), user);
		}
		
		return rtnValue;
    }
	
	
	public int insertDetail(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception
    {        
		
		int rtnValue = 0;
		
		//1. ���������� ���� �Է�
		rtnValue =  maEqCtgAsmbDetailDAO.insertDetail( maEqCtgAsmbDetailDTO, maEqCatalogCommonDTO, user);
		
		//full_desc�� update
		this.updateFullDesc(maEqCtgAsmbDetailDTO, user);
		
		//2. ������ ���������� ������� �ϰ� �߰��ϱ�.
		if("Y".equals(maEqCtgAsmbDetailDTO.getIsEqTypeAsmb()))
        {
		    MaEqMstrListService maEqMstrListService = (MaEqMstrListService) CommonUtil.getBean("maEqMstrListService", user);
		    MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
            
		    //�ش� ���������� �����ִ� �����(�����Ѽ���, ���� ������ �̷� ����� ����)
		    MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setEqCtgId(maEqCatalogCommonDTO.getEqCtgId());
            List<Map> eqList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user);
            for(Map eq:eqList){
                rtnValue += maEqMstrAsmbListService.inputAsmb(maEqCtgAsmbDetailDTO, StringUtil.valueOf(eq.get("EQUIPID")), user);
            }
        }
		
		return rtnValue;
    }
	
	private int updateFullDesc(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, User user) throws Exception
	{
	    int rtnValue = 0;
	    
	    MaEqCtgAsmbListService maEqCtgAsmbListService = (MaEqCtgAsmbListService) CommonUtil.getBean("maEqCtgAsmbListService", user);
	    MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
	    maEqCatalogCommonDTO.setEqCtgId(maEqCtgAsmbDetailDTO.getEqCtgId());
	    List list = CommonUtil.makeFullDesc(maEqCtgAsmbListService.findAsmbList(maEqCatalogCommonDTO, new MaEqCtgAsmbListDTO(), user), maEqCtgAsmbDetailDTO.getEqCtgAsmbId(), "EQCTGASMBID", "PEQCTGASMBID", "EQCTGASMBDESC", "FULLDESC", MaEqCtgAsmbDetailDTO.class);
	    
	    maEqCtgAsmbDetailDAO.updateDetail(list, user);
	    
	    return rtnValue;
	}
}
