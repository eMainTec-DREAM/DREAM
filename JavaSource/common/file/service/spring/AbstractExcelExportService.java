package common.file.service.spring;

import common.file.service.ExcelExportService;

public abstract class AbstractExcelExportService implements ExcelExportService
{    
	protected ExcelExportService excelExportService;
	
    public AbstractExcelExportService(ExcelExportService excelExportService)
    {
        this.excelExportService = excelExportService;
    }
	
	
}
