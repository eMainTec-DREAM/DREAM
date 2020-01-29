package dream.mgr.usrgrp.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthFieldDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthFieldService;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthFieldServiceTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthFieldService"
 * @spring.property name="mgrUsrGrpPageAuthFieldDAO" ref="mgrUsrGrpPageAuthFieldDAO"
 */
public class MgrUsrGrpPageAuthFieldServiceImpl implements MgrUsrGrpPageAuthFieldService
{
	private MgrUsrGrpPageAuthFieldDAO mgrUsrGrpPageAuthFieldDAO = null;
	
	public MgrUsrGrpPageAuthFieldDAO getMgrUsrGrpPageAuthFieldDAO()
    {
        return mgrUsrGrpPageAuthFieldDAO;
    }

    public void setMgrUsrGrpPageAuthFieldDAO(MgrUsrGrpPageAuthFieldDAO mgrUsrGrpPageAuthFieldDAO)
    {
        this.mgrUsrGrpPageAuthFieldDAO = mgrUsrGrpPageAuthFieldDAO;
    }

    @Override
    public List findList(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthFieldDAO.findList(mgrUsrGrpPageAuthFieldDTO, user);
    }
    
    @Override
    public MgrUsrGrpPageAuthFieldDTO findDetail(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception
    {
        Map map = (Map) CommonUtil.makeJson(mgrUsrGrpPageAuthFieldDAO.findList(mgrUsrGrpPageAuthFieldDTO, user)).get(0);
        return (MgrUsrGrpPageAuthFieldDTO) CommonUtil.makeDTO(map, MgrUsrGrpPageAuthFieldDTO.class);
    }

    @Override
    public int updateDetail(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception
    {
        int result = 0;
        String auId = mgrUsrGrpPageAuthFieldDTO.getUgpgfieldauId();
        if("N".equals(mgrUsrGrpPageAuthFieldDTO.getCheckYn())) {
			if("".equals(auId) || auId.equals(null)) {
				mgrUsrGrpPageAuthFieldDTO.setUgpgfieldauId(mgrUsrGrpPageAuthFieldDAO.getNextSequence("SQAUGPGFIELDAU_ID"));
				result = mgrUsrGrpPageAuthFieldDAO.insertAuStatus(mgrUsrGrpPageAuthFieldDTO, user);
			} else 
				result = mgrUsrGrpPageAuthFieldDAO.updateAuStatus(mgrUsrGrpPageAuthFieldDTO, user);
        }
		return result;
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthFieldDAO.findTotalCount(mgrUsrGrpPageAuthFieldDTO, user);
    }

	@Override
	public int updAuthStatus(String[] pgfieldId, String[] ugpgfieldauId, String[] usrgrpId, String changeStatus, User user)
			throws Exception {
		
		int result = 0;

		 if(!pgfieldId.equals(null)) {
           for(int i=0; i<pgfieldId.length; i++)
           {
           	result = result + this.updAuthStatus(pgfieldId[i], ugpgfieldauId[i], usrgrpId[i], changeStatus, user);
           }
       }
       return result;
	}

	private int updAuthStatus(String pgfieldId, String ugpgfieldauId, String usrgrpId, String changeStatus, User user) throws Exception {
		
		int result = 0;
		MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO = new MgrUsrGrpPageAuthFieldDTO();
		mgrUsrGrpPageAuthFieldDTO.setPgfieldId(pgfieldId);
		mgrUsrGrpPageAuthFieldDTO.setUsrgrpId(usrgrpId);
		
		MgrUsrGrpPageAuthFieldDTO authFieldDTO = this.findDetail(mgrUsrGrpPageAuthFieldDTO, user);
		String auId = authFieldDTO.getUgpgfieldauId();
		
		mgrUsrGrpPageAuthFieldDTO.setUsrgrpId(authFieldDTO.getUsrgrpId());
		mgrUsrGrpPageAuthFieldDTO.setPageId(authFieldDTO.getPageId());
		mgrUsrGrpPageAuthFieldDTO.setFieldId(authFieldDTO.getFieldId());
		mgrUsrGrpPageAuthFieldDTO.setAuthLimitTypeId(changeStatus);
		
		if("N".equals(authFieldDTO.getCheckYn())) {
			if("".equals(auId) || auId.equals(null)) {
				mgrUsrGrpPageAuthFieldDTO.setUgpgfieldauId(mgrUsrGrpPageAuthFieldDAO.getNextSequence("SQAUGPGFIELDAU_ID"));
				result = mgrUsrGrpPageAuthFieldDAO.insertAuStatus(mgrUsrGrpPageAuthFieldDTO, user);
			} else 
				result = mgrUsrGrpPageAuthFieldDAO.updateAuStatus(mgrUsrGrpPageAuthFieldDTO, user);
		} 
		
		return result;
	}
}

