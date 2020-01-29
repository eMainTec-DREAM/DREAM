package dream.mgr.usrdata.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrdata.dao.McDataSelectPopupDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcDataSelectPopupDAOTarget"
 * @spring.txbn id="mcDataSelectPopupDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McDataSelectPopupDAOOraImpl extends BaseJdbcDaoSupportOra implements McDataSelectPopupDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public List findList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append(mcDataSelectCommonDTO.getScript());
        
        return getJdbcTemplate().queryForList(query.toString(mcDataSelectCommonDTO.getIsLoadMaxCount(), mcDataSelectCommonDTO.getFirstRow()));
    }

	public void makeLogForScript(McDataSelectCommonDTO mcDataSelectCommonDTO,McDataSelectDetailDTO detailDTO, String errMsg) 
	{
		
		QueryBuffer query = new QueryBuffer();

		query.append("INSERT INTO TAUSRDATALOG(                                      			");
		query.append("   comp_no,     usrdatalog_id,   usrdata_id,    dept_id,    			");
		query.append("   cre_id,      cre_date,       script,       rtn_value           	");
		query.append(")VALUES(                                                    			");
		query.append("   ?,          SQAUSRDATALOG_ID.nextval,      ?,            ?,       	");
		query.append("   ?,          to_char(sysdate,'yyyymmdd'),  ?,            ?			");
		query.append(")                                                            			");

    	
    	Object[] objects = new Object[] {
    			detailDTO.getCompNo(),
    			detailDTO.getUsrrptId(),
    			detailDTO.getCreDept(),
    			detailDTO.getCreBy(),
    			mcDataSelectCommonDTO.getScript(),
    			errMsg
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}
	
    /**
     * find count
     * @author  js.lee
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        query.append("  SELECT count(1)				 ");
        query.append("  FROM (						 ");
        query.append(mcDataSelectCommonDTO.getScript());
        query.append("  )							 ");
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    @Override
    public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       title title,                                       ");
        query.append("       script script,                                     ");
        query.append("       usrdata_id usrrptId                                ");
        query.append("FROM   TAUSRDATA x                                        ");
        query.append("WHERE  1 = 1                                              ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.usrdata_id", mcDataSelectCommonDTO.getUsrrptId());
        
        mcDataSelectCommonDTO = 
                (McDataSelectCommonDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new McDataSelectCommonDTO()));
        
        return mcDataSelectCommonDTO;
    }
	
}