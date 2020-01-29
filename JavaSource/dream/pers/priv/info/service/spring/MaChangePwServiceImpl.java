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
 * ��й�ȣ����
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
        
        // 8~12�ڸ�, ����,����,Ư������ ���� 1�� �̻� ����
        String regPt = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,12}$";
        
        MaChangePwDTO compareDTO = this.findDetail(maChangePwDTO);
        // ����Ǿ��ִ� ���� ��й�ȣ�� �Է��� ������й�ȣ�� ������ ��
        String oldPw = CommonUtil.aesEncodeString(maChangePwDTO.getOldPw());
        rtnMap.put("RESULT", "FAIL");
        
        if(!oldPw.equals(compareDTO.getOldPwTemp())){
            rtnMap.put("CODE", "matchFalse");
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0002"));
            // ����� ��й�ȣ�� ���� ��й�ȣ�� �ٸ��ϴ�.
        }
        // ����й�ȣ�� Ȯ�κ�й�ȣ�� ������ ��
        else if (!maChangePwDTO.getNewPw().equals(maChangePwDTO.getConfirmPw())) {
            // Ȯ�� ��й�ȣ�� �� ��й�ȣ�� �ٸ��ϴ�.
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0001"));
            rtnMap.put("CODE", "confirmFalse");

        }
        // ��й�ȣ ���⵵ üũ
        else if ("Y".equals(MwareConfig.getIsPwSafetyLength()) && Pattern.matches(regPt,maChangePwDTO.getNewPw()) == false) {
            // ��й�ȣ�� ����,����,Ư�����ڸ� ������ 8~12�ڸ� ��� �����մϴ�.
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG220"));
            rtnMap.put("CODE", "regFalse");

        }
        // ��й�ȣ ���� üũ
        else if (!(maChangePwDTO.getNewPw().trim().length() > 0)) {
            // ��й�ȣ�� �������� �����Ͻ� �� �����ϴ�.
            rtnMap.put("MESSAGE", MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG221"));
            rtnMap.put("CODE", "blankFalse");
  
        }
        else {
            // �ֱٿ� ����� ��й�ȣ���� üũ
            if (!"0".equals(MwareConfig.getPwChangeHistCnt())){
                if(!"0".equals(this.checkPwHist(maChangePwDTO, MwareConfig.getPwChangeHistCnt()))){
                 // �ֱ� n�� �̳��� ����ߴ� ��й�ȣ�� ����� �� �����ϴ�.
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
