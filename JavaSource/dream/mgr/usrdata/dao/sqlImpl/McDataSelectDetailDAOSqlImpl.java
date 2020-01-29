package dream.mgr.usrdata.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.usrdata.dao.McDataSelectDetailDAO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="mcDataSelectDetailDAOTarget"
 * @spring.txbn id="mcDataSelectDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McDataSelectDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements McDataSelectDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public McDataSelectDetailDTO findDetail(String usrrpt_id, String lang)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       dbo.SFACODE_TO_DESC(x.usrdata_type,'USRDATA_TYPE','SYS','','ko')    usrdataTypeDesc,   ");
        query.append("       usrdata_type usrdataType,                          ");
        query.append("       title,                                             ");
        query.append("       description,                                       ");
        query.append("       cre_date creDate,                                  ");
        query.append("       dept_id creDept,                                   ");
        query.append("       (SELECT description                                ");
        query.append("        FROM   TADEPT a                                   ");
        query.append("        WHERE  a.dept_id = x.dept_id) creDeptDesc,        ");
        query.append("       cre_id creBy,                                      ");
        query.append("       comp_no compNo,                                    ");
        query.append("       (SELECT emp_name                                   ");
        query.append("        FROM   TAEMP                                      ");
        query.append("        WHERE  emp_id = x.cre_id) creByDesc,              ");
        query.append("       usrdata_id usrrptId                                    ");
        query.append("FROM   TAUSRDATA x                                            ");
        query.append("WHERE  x.usrdata_id = '"+usrrpt_id+"'                         ");

    
        McDataSelectDetailDTO DetailDTO = 
        		
        		(McDataSelectDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new McDataSelectDetailDTO()));
        
        return DetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int insertDetail(McDataSelectDetailDTO DetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAUSRDATA                            ");
        query.append("(     usrdata_id,      usrrpt_type,            ");
        query.append("      comp_no,                                ");
        query.append("      usrdata_type,   dept_id,                ");
        query.append("      cre_id,         cre_date,               ");
        query.append("      title,          description)            ");
        query.append("     VALUES                                   ");
        query.append("(     ?,              ?,                      ");
        query.append("      ?,                                      ");
        query.append("      ?,              ?,                      ");
        query.append("      ?,              ?,                      ");
        query.append("      ?,              ?                       ");
        query.append(")                                             ");

    	
        Object[] objects = new Object[] {
                DetailDTO.getUsrrptId(),
                DetailDTO.getUsrrptType(),
                loginUser.getCompNo(),
                DetailDTO.getUsrdataType(),
                DetailDTO.getCreDept(),
                DetailDTO.getCreBy(),
                DetailDTO.getCreDate(),
                DetailDTO.getTitle(),
                DetailDTO.getDescription()

        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int updateDetail(McDataSelectDetailDTO DetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSRDATA SET    ");
        query.append("  usrrpt_type = ?,    ");
        query.append("  usrdata_type= ?,    ");
        query.append("  dept_id     = ?,    ");
        query.append("  cre_id      = ?,    ");
        query.append("  cre_date    = ?,    ");
        query.append("  title       = ?,    ");
        query.append("  description = ?     ");
        query.append("WHERE usrdata_id = ?  ");
        
        Object[] objects = new Object[] {
                DetailDTO.getUsrrptType(),
                DetailDTO.getUsrdataType(),
                DetailDTO.getCreDept(),
                DetailDTO.getCreBy(),
                DetailDTO.getCreDate(),
                DetailDTO.getTitle(),
                DetailDTO.getDescription(),
                DetailDTO.getUsrrptId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}