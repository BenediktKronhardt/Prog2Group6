package de.hsba.bi.demo6.rating;

import org.springframework.data.jpa.repository.JpaRepository;

interface RatingRepository extends JpaRepository<Rating, Long> {}
