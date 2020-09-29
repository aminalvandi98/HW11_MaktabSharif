package ir.maktabsharif.repository;

import ir.maktabsharif.entities.Role;

import javax.persistence.TypedQuery;

public class RoleRepository extends Repository<Role, Integer> {
    private static RoleRepository roleRepository;

    private RoleRepository() {

    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    public Role findRole(String title) {
        Role role = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Role> query = entityManager.createQuery("select r from Role r where title=:t", Role.class);
            query.setParameter("t", title);
            role = query.getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    public static RoleRepository getRoleRepository() {
        if (roleRepository == null) roleRepository = new RoleRepository();
        return roleRepository;
    }
}
