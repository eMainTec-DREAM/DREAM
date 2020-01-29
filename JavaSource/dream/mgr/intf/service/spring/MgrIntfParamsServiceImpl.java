package dream.mgr.intf.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.util.CommonUtil;
import dream.mgr.intf.dao.MgrIntfParamsDAO;
import dream.mgr.intf.dto.IntfUserExecVO;
import dream.mgr.intf.dto.IntfUserExecValueVO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.service.MgrIntfParamsService;

/**
 * 인터페이스 파라미터
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mgrIntfParamsServiceTarget"
 * @spring.txbn id="mgrIntfParamsService"
 * @spring.property name="mgrIntfParamsDAO" ref="mgrIntfParamsDAO"
 */
public class MgrIntfParamsServiceImpl implements MgrIntfParamsService
{
    private MgrIntfParamsDAO mgrIntfParamsDAO = null;
    
    public MgrIntfParamsDAO getMgrIntfParamsDAO() 
    {
		return mgrIntfParamsDAO;
	}

	public void setMgrIntfParamsDAO(MgrIntfParamsDAO mgrIntfParamsDAO) 
	{
		this.mgrIntfParamsDAO = mgrIntfParamsDAO;
	}

	public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user)throws Exception
    {
        return mgrIntfParamsDAO.findList(mgrIntfCommonDTO, user);
    }

    @Override
    public JSONObject execList(List<Map> gridList, MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception
    {
        IntfUserExecValueVO intfUserExecValueVO = null;
        JSONObject jsonObj = new JSONObject();
        boolean isValid = true;
        List invalidIds = new ArrayList<String>();
        Map arguments = new HashMap<String, String>();
        
        String intfId = mgrIntfCommonDTO.getIntfId();
        String intfUserExecId = "";
        
        //TAINTFUSEREXEC 저장 merge
        IntfUserExecVO intfUserExecVO = mgrIntfParamsDAO.selectTAINTFUSEREXEC(mgrIntfCommonDTO, user);
        
        if("".equals(intfUserExecVO.getIntfUserExecId())) {
            intfUserExecId = mgrIntfParamsDAO.getNextSequence("SQAINTFUSEREXEC_ID");
            intfUserExecVO.setCompNo(user.getCompNo());
            intfUserExecVO.setIntfUserExecId(intfUserExecId);
            intfUserExecVO.setIntfId(intfId);
            intfUserExecVO.setExeBy(user.getUserId());
            mgrIntfParamsDAO.insertTAINTFUSEREXEC(intfUserExecVO);
        }
        else {
            intfUserExecId = intfUserExecVO.getIntfUserExecId();
        }
        
        if(gridList.size()==1 && "".equals(gridList.get(0).get("INTFMAPID"))) gridList.clear();
        
        for(Map map : gridList)
        {
            arguments.put(map.get("SRCFIELDTYPE"), map.get("SRCFIELDVALUE"));
            intfUserExecValueVO = (IntfUserExecValueVO)CommonUtil.makeDTO(map, IntfUserExecValueVO.class);
            intfUserExecValueVO.setCompNo(user.getCompNo());
            intfUserExecValueVO.setIntfUserExecId(intfUserExecId);
            intfUserExecValueVO.setIntfId(intfId);
            
            //필수여부, size validation
            String srcFieldValue = intfUserExecValueVO.getSrcFieldValue();
            String srcIsNotNull = map.get("SRCISNOTNULL").toString();
            String srcFieldSize = map.get("SRCFIELDSIZE").toString();
            String rowId = map.get("gr").toString();
            if("Y".equals(srcIsNotNull) && "".equals(srcFieldValue)) {
                isValid = false;
                invalidIds.add(rowId);
                continue;
            }
            if(srcFieldSize.matches("^[0-9][0-9]*$")) {
                int size = Integer.parseInt(srcFieldSize);
                if(srcFieldValue.length() > size) {
                    isValid = false;
                    invalidIds.add(rowId);
                    continue;
                }
            }
            
            //TAINTFUSEREXECVALUE 저장 merge
            if("".equals(mgrIntfParamsDAO.selectTAINTFUSEREXECVALUE(intfUserExecValueVO).getIntfUserExecValueId())) {
                intfUserExecValueVO.setIntfUserExecValueId(mgrIntfParamsDAO.getNextSequence("SQAINTFUSEREXECVAlUE_ID"));
                mgrIntfParamsDAO.insertTAINTFUSEREXECVALUE(intfUserExecValueVO);
            }
            else {
                mgrIntfParamsDAO.updateTAINTFUSEREXECVALUE(intfUserExecValueVO);
            }
        }
        
        if(isValid){
            //interface service call
            try{
                CommonUtil.intfCall(mgrIntfCommonDTO.getIntfNo(), arguments, user);
                jsonObj.put("valid", "Y");
            } catch(SqlIgnoreException e){
                jsonObj.put("valid", "N");
            }
        }
        else {
            jsonObj.put("valid", "N");
            jsonObj.put("invalidIds", invalidIds);
        }
        
        return jsonObj;
    }
}
