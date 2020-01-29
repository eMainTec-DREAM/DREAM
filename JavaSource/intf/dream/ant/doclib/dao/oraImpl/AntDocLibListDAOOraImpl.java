package intf.dream.ant.doclib.dao.oraImpl;

import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportOra;
import intf.dream.ant.doclib.dao.AntDocLibListDAO;
import intf.dream.ant.doclib.dto.AntDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  kim21017
 * @version $Id: AntDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="antDocLibListDAOTarget"
 * @spring.txbn id="antDocLibListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntDocLibListDAOOraImpl extends BaseJdbcDaoSupportOra implements AntDocLibListDAO
{
    public int insertFileInfo(MwareFile mwareFile, AntDocLibCommonDTO antDocLibCommonDTO)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}