package reader.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reader.csv.entites.Cell;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {
}
