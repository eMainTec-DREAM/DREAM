package intf.dream.android.offline.madoclib.dao.sqlImpl;

import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportSql;
import intf.dream.android.offline.madoclib.dao.AnIfDocLibListDAO;
import intf.dream.android.offline.madoclib.dto.AnIfDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  kim21017
 * @version $Id: IfDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="anIfDocLibListDAOTarget"
 * @spring.txbn id="anIfDocLibListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfDocLibListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnIfDocLibListDAO
{
    public int insertFileInfo(MwareFile mwareFile, AnIfDocLibCommonDTO anIfDocLibCommonDTO)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}