package net.servletDatabase.model;

import com.aggregator.Controller;
import com.aggregator.model.*;
import com.aggregator.view.DatabaseView;
import com.aggregator.view.View;

public class GoToAggregVacationAndCreateNewDatabase {

    public void aggregatorCreate(String typeVacanсy, String city,String nameDatabase ,boolean newDatabase) {


        Provider[] providers={new Provider(new RabotaStrategy()), new Provider(new DouStrategy()), new Provider(new HHStrategy()), new Provider(new WorkUaStrategy())};


//        HtmlView htmlView = new HtmlView();
        DatabaseView databaseView=new DatabaseView();
        View[] views={databaseView};


        Model model=new Model(views, providers);


//
        Controller controller=new Controller(model);



        controller.onCitySelectAndTypeVacancy(typeVacanсy, city, nameDatabase, newDatabase );

        databaseView.setController(controller);
    }
}
