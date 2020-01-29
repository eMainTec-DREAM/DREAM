package dream.mgr.user.service.spring;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.util.CommonUtil;
import common.util.MailUtil;
import common.util.StringUtil;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.mgr.user.dao.MaUserDetailDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserDetailDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;
import dream.mgr.user.service.MaUserDetailService;
import dream.mgr.user.service.MaUserListService;
import dream.mgr.user.service.MgrUserPlantauthDetailService;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.service.MaEmpDetailService;

/**
 * 사용자 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maUserDetailServiceTarget"
 * @spring.txbn id="maUserDetailService"
 * @spring.property name="maUserDetailDAO" ref="maUserDetailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaUserDetailServiceImpl implements MaUserDetailService
{
    private MaUserDetailDAO maUserDetailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    
    public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaUserDetailDAO getMaUserDetailDAO() 
    {
		return maUserDetailDAO;
	}

	public void setMaUserDetailDAO(MaUserDetailDAO maUserDetailDAO) 
	{
		this.maUserDetailDAO = maUserDetailDAO;
	}

	public MaUserDetailDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)throws Exception
    {
	    MaUserListService maUserListService = (MaUserListService) CommonUtil.getBean("maUserListService", loginUser);
        return (MaUserDetailDTO) CommonUtil.makeDetailFromList(maUserListService.findUserList(maUserCommonDTO, loginUser), new MaUserDetailDTO());
    }
    
	public int insertDetail(MaUserDetailDTO maUserDetailDTO, User loginUser) throws Exception
    {        
	    String resetPassword = StringUtil.randomString(8);
	    maUserDetailDTO.setResetPassword(resetPassword);
	    maUserDetailDTO.setPassword(maUserDetailDTO.getUserNo().toLowerCase());
	    String base32Symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
	    maUserDetailDTO.setOtpKey(StringUtil.randomString(16, base32Symbols));
	    maUserDetailDTO.setUserNo(maUserDetailDTO.getUserNo().toUpperCase());

	    int rtn = maUserDetailDAO.insertDetail(maUserDetailDTO, loginUser);
        
        //등록한 EMP의 소속 공장의 권한을 부여
        String empId = maUserDetailDTO.getEmpId();
        if(!"".equals(empId)){
            MaEmpDetailService maEmpDetailService = (MaEmpDetailService) CommonUtil.getBean("maEmpDetailService", loginUser);
            MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
            maEmpCommonDTO.setEmpId(empId);
            String plant = maEmpDetailService.findDetail(maEmpCommonDTO, loginUser).getPlantId();
            if(!"".equals(plant)){
                MgrUserPlantauthDetailService mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService) CommonUtil.getBean("mgrUserPlantauthDetailService", loginUser);
                MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = new MgrUserPlantauthDetailDTO();
                mgrUserPlantauthDetailDTO.setUserId(maUserDetailDTO.getUserId());
                mgrUserPlantauthDetailDTO.setPlant(plant);
                mgrUserPlantauthDetailDTO.setIsAuth("Y");
                mgrUserPlantauthDetailDTO.setUsrplantauthId(maUserDetailDAO.getNextSequence("SQAUSRPLANTAUTH_ID"));
                mgrUserPlantauthDetailService.insertDetail(mgrUserPlantauthDetailDTO, loginUser);
            }
        }
        
        return rtn;
    }
	
	public int updateDetail(MaUserDetailDTO maUserDetailDTO, User loginUser) throws Exception
    {        
	    String base32Symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
        maUserDetailDTO.setOtpKey(StringUtil.randomString(16, base32Symbols));
        return maUserDetailDAO.updateDetail(maUserDetailDTO, loginUser);
    }
	
	public String validUserNo(MaUserDetailDTO maUserDetailDTO, User loginUser) throws Exception
    {
        return maUserDetailDAO.validUserNo(maUserDetailDTO, loginUser);
    }

    @Override
    public int sendUserInfoMail(MaUserCommonDTO maUserCommonDTO, User user) throws Exception
    {
        // return 0:정상, -1:메일서비스 미등록, -2:전송대상이없음
        
        MaUserDetailDTO maUserDetailDTO = this.findDetail(maUserCommonDTO, user);
        
        //메일 주소가 있는지 판단
        if ("".equals(maUserDetailDTO.getEmail())) return -2;
        
        String result = "";
        try{
            result = MailUtil.sendMail("USR10", maUserDetailDTO.getUserId(), user);
        }catch(SqlIgnoreException e){
            e.printStackTrace();
        }
        
        if("2".equals(result)) return -1;
        
        return 0;
    }
    
    public int sendUserPwMail(MaUserCommonDTO maUserCommonDTO, User user) throws Exception
    {
        // return 0:정상, -1:메일서비스 미등록, -2:전송대상이없음
        
        MaUserDetailDTO maUserDetailDTO = this.findDetail(maUserCommonDTO, user);
        String resetPassword = StringUtil.randomString(8);
        maUserDetailDTO.setResetPassword(resetPassword);
        
        maUserDetailDAO.updateRpw(maUserDetailDTO);
        
        //메일 주소가 있는지 판단
        if ("".equals(maUserDetailDTO.getEmail())) return -2;
        
        String result = "";
        try{
            result = MailUtil.sendMail("USR20", maUserDetailDTO.getUserId(), user);
        }catch(SqlIgnoreException e){
            e.printStackTrace();
        }
        
        if("2".equals(result)) return -1;
        
        return 0;
    }
    
    @Override
    public String getNextSequence() throws Exception
    {
        return maUserDetailDAO.getNextSequence("SQAUSER_ID");
    }
}
