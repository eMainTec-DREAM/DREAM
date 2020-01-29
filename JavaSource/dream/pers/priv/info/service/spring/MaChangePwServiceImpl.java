package dream.pers.priv.info.service.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.MessageUtil;
import dream.pers.priv.info.dao.MaChangePwDAO;
import dream.pers.priv.info.dto.MaChangePwDTO;
import dream.pers.priv.info.service.MaChangePwService;

/**
 * 비밀번호변경
 * @author kim2107
 * @version $Id: MaChangePwServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maChangePwServiceTarget"
 * @spring.txbn id="maChangePwService"
 * @spring.property name="maChangePwDAO" ref="maChangePwDAO"
 */
public class MaChangePwServiceImpl implements MaChangePwService
{
    private MaChangePwDAO maChangePwDAO = null;
    
    public MaChangePwDAO getMaChangePwDAO() {
		return maChangePwDAO;
	}

	public void setMaChangePwDAO(MaChangePwDAO maChangePwDAO) {
		this.maChangePwDAO = maChangePwDAO;
	}

	public MaChangePwDTO findDetail(MaChangePwDTO maChangePwDTO)throws Exception
    {
        return maChangePwDAO.findDetail(maChangePwDTO);
    }
    
	public int updateDetail(MaChangePwDTO maChangePwDTO) throws Exception
    {        
		maChangePwDAO.insertPwChanHist(maChangePwDTO);
        return maChangePwDAO.updateDetail(maChangePwDTO);
    }
	
	public String checkPwHist(MaChangePwDTO maChangePwDTO, String pwChangeHistCnt) throws Exception
	{        
		return maChangePwDAO.checkPwHist(maChangePwDTO, pwChangeHistCnt);
	}

    @Override
    public Map<String, String> checkPassword(MaChangePwDTO maChangePwDTO, User user) throws Exception
    {
        Map<String,String> rtnMap = new HashMap<String,String>();
        
        // 8~12자리, 숫자,문자,특수문자 각각 1개 이상 포함
        String regPt = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,12}$";
        
        MaChangePwDTO compareDTO = this.findDetail(maChangePwDTO);
        // 저장되어있는 기존 비밀번호가 입력한 기존비밀번호와 같은지 비교
        String oldPw = CommonUtil.aesEncodeString(maChangePwDTO.getOldPw());
        rtnMap.put("RESULT", "FAIL");
        
        if(!oldPw.equals(compareDTO.getOldPwTemp())){
            rtnMap.put("CODE", "matchFalse");
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0002"));
            // 사용자 비밀번호와 기존 비밀번호가 다릅니다.
        }
        // 새비밀번호와 확인비밀번호가 같은지 비교
        else if (!maChangePwDTO.getNewPw().equals(maChangePwDTO.getConfirmPw())) {
            // 확인 비밀번호가 새 비밀번호와 다릅니다.
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0001"));
            rtnMap.put("CODE", "confirmFalse");

        }
        // 비밀번호 복잡도 체크
        else if ("Y".equals(MwareConfig.getIsPwSafetyLength()) && Pattern.matches(regPt,maChangePwDTO.getNewPw()) == false) {
            // 비밀번호는 숫자,영문,특수문자를 포함한 8~12자만 사용 가능합니다.
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG220"));
            rtnMap.put("CODE", "regFalse");

        }
        // 비밀번호 공백 체크
        else if (!(maChangePwDTO.getNewPw().trim().length() > 0)) {
            // 비밀번호를 공백으로 지정하실 수 없습니다.
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG221"));
            rtnMap.put("CODE", "blankFalse");
  
        }
        else {
            // 최근에 사용한 비밀번호인지 체크
            if (!"0".equals(MwareConfig.getPwChangeHistCnt())){
                if(!"0".equals(this.checkPwHist(maChangePwDTO, MwareConfig.getPwChangeHistCnt()))){
                 // 최근 n번 이내에 사용했던 비밀번호는 사용할 수 없습니다.
                    rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0251")+MwareConfig.getPwChangeHistCnt()+MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0252"));
                    rtnMap.put("CODE", "pwHistCntFalse");
                    
                } else {
                    rtnMap.put("MESSAGE","");
                    rtnMap.put("RESULT", "SUCCESS");
                    this.updateDetail(maChangePwDTO);
                }
            } else {
                rtnMap.put("MESSAGE","");
                rtnMap.put("RESULT", "SUCCESS");
                this.updateDetail(maChangePwDTO);
            }
        }
        
        return rtnMap;
    }
}
