package csd214.bookstore.services;

import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.pojos.ActionGame;
import csd214.bookstore.repositories.IRepository;

import java.util.List;


public class ActionGameService {
    private final IRepository<ProductEntity> repository;

    public ActionGameService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public void launchMultiplayerExpansion(Long id, double priceSurcharge) {
        ProductEntity entity = repository.findById(id);
        System.out.println("Error: Action Game with id " + id + " not found");
    }
    public void bulkRestockActionTitle(String name, String platform, double price,  int count){
        for (int i=0; i< count; i++){
            ActionGame newGame = new ActionGame(name, platform, price, i);
            repository.save(newGame);
        }
    }
    public List<ProductEntity> getAllActionGames() {
        return repository.findAll();
    }
}
