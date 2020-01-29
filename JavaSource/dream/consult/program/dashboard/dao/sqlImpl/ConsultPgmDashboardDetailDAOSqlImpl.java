package dream.consult.program.dashboard.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.dashboard.dao.ConsultPgmDashboardDetailDAO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardDetailDTO;

/**
 * 대시보드 Contents - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultPgmDashboardDetailDAOTarget"
 * @spring.txbn id="consultPgmDashboardDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmDashboardDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmDashboardDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardCommonDTO
     * @param user
     * @return
     */
    public ConsultPgmDashboardDetailDTO findDetail(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 														");
        query.append("		x.dbcontents_id AS dbContentsId							");
        query.append("		,x.dbcontents_type AS dbContentsTypeId					");
        query.append("		,dbo.SFACODE_TO_DESC(x.dbcontents_type,'DBCONTENTS_TYPE','SYS','','"+user.getLangId()+"') AS dbContentsTypeDesc					");
        query.append("		,x.description AS dbContentsDesc						");
        query.append("		,x.key_type As keyType									");
        query.append("		,x.key_no As keyNo										");
        query.append("		,(SELECT a.key_name FROM TALANG a WHERE a.key_type=x.key_type AND a.key_no = x.key_no AND a.lang = '"+user.getLangId()+"') AS keyName	");
        query.append("		,x.page_id As pageId									");
        query.append("		,(SELECT a.description FROM TAPAGE a WHERE a.page_id = x.page_id ) As pageDesc			");
        query.append("		,x.file_name As fileName								");
        query.append("		,x.dbcontents_width AS dbContentsWidthId				");
        query.append("		,dbo.SFACODE_TO_DESC(x.dbcontents_width,'DBCONTENTS_WIDTH','SYS','','"+user.getLangId()+"') AS dbContentsWidthDesc				");
        query.append("		,x.image_file AS imageFile								");
        query.append("		,x.is_use AS isUseId									");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS','','"+user.getLangId()+"') AS isUseDesc		");
        query.append("		,x.remark AS remark										");
        query.append("FROM TADBCONTENTS	x											");
        query.append("WHERE 1=1														");
        query.append("AND dbcontents_id = ?											");
        
        Object[] objects = new Object[] {
        		consultPgmDashboardCommonDTO.getDbContentsId()
    	};
        
        ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO =
        		(ConsultPgmDashboardDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultPgmDashboardDetailDTO()));

        return consultPgmDashboardDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TADBCONTENTS	(									");
    	query.append("		DBCONTENTS_ID	,DBCONTENTS_TYPE	,DBCONTENTS_WIDTH	");
    	query.append("		,DESCRIPTION	,FILE_NAME			,IMAGE_FILE			");
    	query.append("		,IS_USE			,KEY_NO				,KEY_TYPE			");
    	query.append("		, PAGE_ID		,REMARK									");
    	query.append("			)													");
    	query.append("VALUES (														");
    	query.append("		 ?,?,?													");
    	query.append("		,?,?,?													");
    	query.append("		,?,?,?													");
    	query.append("		,?,?													");
    	query.append("		)														");

    	Object[] objects = new Object[] {
    			consultPgmDashboardDetailDTO.getDbContentsId()
    			,consultPgmDashboardDetailDTO.getDbContentsTypeId()
    			,consultPgmDashboardDetailDTO.getDbContentsWidthId()
    			,consultPgmDashboardDetailDTO.getDbContentsDesc()
    			,consultPgmDashboardDetailDTO.getFileName()
    			,consultPgmDashboardDetailDTO.getImageFile()
    			,consultPgmDashboardDetailDTO.getIsUseId()
    			,consultPgmDashboardDetailDTO.getKeyNo()
    			,consultPgmDashboardDetailDTO.getKeyType()
    			,consultPgmDashboardDetailDTO.getPageId()
    			,consultPgmDashboardDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TADBCONTENTS SET				");
    	query.append("			dbcontents_type   = ?		");
    	query.append("			,description      = ?		");
    	query.append("			,key_type         = ?		");
    	query.append("			,key_no           = ?		");
    	query.append("			,page_id          = ?		");
    	query.append("			,file_name        = ?		");
    	query.append("			,dbcontents_width = ?		");
    	query.append("			,image_file       = ?		");
    	query.append("			,is_use           = ?		");
    	query.append("			,remark           = ?		");
    	query.append("WHERE 1=1								");
    	query.append("AND dbcontents_id = ?					");

    	Object[] objects = new Object[] {
    			consultPgmDashboardDetailDTO.getDbContentsTypeId()
    			,consultPgmDashboardDetailDTO.getDbContentsDesc()
    			,consultPgmDashboardDetailDTO.getKeyType()
    			,consultPgmDashboardDetailDTO.getKeyNo()
    			,consultPgmDashboardDetailDTO.getPageId()
    			,consultPgmDashboardDetailDTO.getFileName()
    			,consultPgmDashboardDetailDTO.getDbContentsWidthId()
    			,consultPgmDashboardDetailDTO.getImageFile()
    			,consultPgmDashboardDetailDTO.getIsUseId()
    			,consultPgmDashboardDetailDTO.getRemark()
    			,consultPgmDashboardDetailDTO.getDbContentsId()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }
}