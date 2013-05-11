package racetrack

import pl.touk.excel.export.XlsxExporter

class RunnerController {

//    def index() {}

    def scaffold = true

    def export(){

        List<Runner> products = Runner.getAll()
        def withProperties = ['firstName','lastName']
        new XlsxExporter('/tmp/myReportFile.xlsx').add(products, withProperties).save()
        render products
    }

}

