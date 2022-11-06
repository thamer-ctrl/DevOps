package tn.esprit.rh.achat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.rh.achat.entities.Fournisseur;

// Comment for branch_thamer
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
