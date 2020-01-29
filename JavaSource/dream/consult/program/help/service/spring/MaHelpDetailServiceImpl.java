package dream.consult.program.help.service.spring;

import common.bean.User;
import common.util.MailUtil;
import dream.consult.program.help.dao.MaHelpDetailDAO;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.dto.MaHelpDetailDTO;
import dream.consult.program.help.service.MaHelpDetailService;
import dream.mgr.message.service.MgrMessageTransDetailService;

/**
 * �� serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maHelpDetailServiceTarget"
 * @spring.txbn id="maHelpDetailService"
 * @spring.property name="maHelpDetailDAO" ref="maHelpDetailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaHelpDetailServiceImpl implements MaHelpDetailService
{
    private MaHelpDetailDAO maHelpDetailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    
    public MaHelpDetailDAO getMaHelpDetailDAO() 
    {
		return maHelpDetailDAO;
	}

	public void setMaHelpDetailDAO(MaHelpDetailDAO maHelpDetailDAO) 
	{
		this.maHelpDetailDAO = maHelpDetailDAO;
	}

	public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaHelpDetailDTO findDetail(MaHelpCommonDTO maHelpCommonDTO)throws Exception
    {
        return maHelpDetailDAO.findDetail(maHelpCommonDTO);
    }
    
	public int insertDetail(MaHelpDetailDTO maHelpDetailDTO) throws Exception
    {        
	    int rtn = maHelpDetailDAO.insertDetail(maHelpDetailDTO);
	    
	    String goWrkImpId = maHelpDetailDAO.getNextSequence("SQAGOWRKIMP_ID");
        maHelpDetailDTO.setGowrkimpId(goWrkImpId);
        maHelpDetailDTO.setGowrkimpNo(goWrkImpId);
        maHelpDetailDAO.insertGowrkimp(maHelpDetailDTO);
	    
        return rtn;
    }
	
	public int updateDetail(MaHelpDetailDTO maHelpDetailDTO) throws Exception
    {        
	    User user = maHelpDetailDTO.getLoginUser();
	    int rtn = maHelpDetailDAO.updateDetail(maHelpDetailDTO);
	    
	    switch (maHelpDetailDTO.getHelpdeskStatus())
	    {
	        case "WT":
	            maHelpDetailDTO.setGowrkimpStatus("WT");
	            break;
	        case "RQ":
	            maHelpDetailDTO.setGowrkimpStatus("WT");
                break;
	        case "RV":
	            maHelpDetailDTO.setGowrkimpStatus("RV");
                break;
	        case "WK":
	            maHelpDetailDTO.setGowrkimpStatus("WK");
                break;
	        case "CP":
	            maHelpDetailDTO.setGowrkimpStatus("CP");
                break;
	        default:
	            maHelpDetailDTO.setGowrkimpStatus("WT");
                break;
	    }
	    maHelpDetailDAO.updateGowrkimp(maHelpDetailDTO);
	    
	    if("CP".equals(maHelpDetailDTO.getHelpdeskStatus())) {
	        //���ϸ� ���� Y�̸� ��û�� �Ϸ�Ǿ��� �� ��û�ڿ��� �Ϸ� ������ ����.
	        MailUtil.sendMail("QNA20", maHelpDetailDTO.getHelpdeskId(), user);
	    }
	    
        return rtn;
    }
	public void requestDetail(MaHelpDetailDTO maHelpDetailDTO) throws Exception
	{
	    maHelpDetailDTO.setHelpdeskStatus("RQ");
	    this.updateDetail(maHelpDetailDTO);
	}
}
